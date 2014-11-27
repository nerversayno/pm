package cn.lcf.dao.impl;

import cn.lcf.controller.HelloController;
import cn.lcf.controller.TicketController;
import cn.lcf.dao.AbstractBaseRedisDao;
import cn.lcf.dao.ITicketPool;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 9:59
 * Description:
 */
public class TicketPoolDao extends AbstractBaseRedisDao<String, String> implements ITicketPool {
    @Override
    public synchronized List<String> get(int count) {
        BoundListOperations<String, String> boundListOperations
                = redisTemplate.boundListOps("ticketPool");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            String str = boundListOperations.rightPop();
            if (str == null) {
                throw new RuntimeException("票已售完");
            }
            list.add(str);
        }
        return list;
    }

    @Override
    public String getOne() {

        BoundListOperations<String, String> boundListOperations
                = redisTemplate.boundListOps("ticketPool");
        String str = boundListOperations.rightPop();
        return str ;
    }

    @Override
    public List<String> add(long count) {
        BoundListOperations<String, String> boundListOperations
                = redisTemplate.boundListOps("ticketPool");
        List<String> list = new ArrayList<String>();
        synchronized (TicketController.class) {
            for (int i = 0; i < count; i++) {
                boundListOperations.leftPush(++TicketController.CURRENT_NO + "");
            }
        }
        return list;
    }

    @Override
    public long length() {
        Long len = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize("ticketPool");
                return connection.lLen(key);
            }
        });
        return len;
    }
}
