package cn.lcf.dao.impl;

import cn.lcf.controller.TicketController;
import cn.lcf.dao.AbstractBaseRedisImpl;
import cn.lcf.dao.ITicketPool;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 15:34
 * Description:
 */
public class TicketPoolImpl extends AbstractBaseRedisImpl implements ITicketPool {

    @Override
    public List<String> get(int count) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            list.add(pop());
        }
        return list;
    }

    @Override
    public String getOne() {
        return  pop();
    }

    private String pop() {
        Response<String> response = pipeline.rpop(TICKET_POOL);
        pipeline.sync();
        return response.get();
    }

    @Override
    public List<String> add(long count) {
        long base = length();
        long times = count / PIECE_NUMBER;
        long left = count % PIECE_NUMBER;
        if (times > 0) {
            for (int i = 0; i < times; i++) {
                for (int j = 0; j < PIECE_NUMBER; j++) {
                    pipeline.lpush(TICKET_POOL, ++base+"");
                }
                pipeline.sync();
            }
        }
        if (left > 0) {
            for (int j = 0; j < left; j++) {
                pipeline.lpush(TICKET_POOL, ++base + "");
            }
            pipeline.sync();
        }
        return null;
    }

    @Override
    public long length() {
        Response<Long> response= pipeline.llen(TICKET_POOL);
        pipeline.sync();
        return response.get();
    }
}
