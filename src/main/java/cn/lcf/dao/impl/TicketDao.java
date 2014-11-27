package cn.lcf.dao.impl;

import cn.lcf.dao.AbstractBaseRedisDao;
import cn.lcf.dao.ITicket;
import cn.lcf.entity.Ticket;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 9:05
 * Description:
 */
public class TicketDao extends AbstractBaseRedisDao<String,String> implements ITicket {


    @Override
    public boolean save(final Ticket ticket) {

        long i  = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] key = serializer.serialize("ticket");
                byte[] value = serializer.serialize(ticket.toRedisString()) ;
                return connection.lPush(key,value);
            }
        });
        return i > 0;
    }

    @Override
    public long save(List<Ticket> ticketList) {
        BoundListOperations<String,String> boundListOps = redisTemplate.boundListOps("ticket");
        long count =0;
        for(Ticket ticket : ticketList){
            boundListOps.leftPush(ticket.toRedisString());
            count ++;
        }
        return count;
    }
}
