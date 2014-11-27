package cn.lcf.dao.impl;

import cn.lcf.dao.AbstractBaseRedisImpl;
import cn.lcf.dao.ITicketPool;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 15:34
 * Description:
 */
public class TicketPoolMS extends AbstractBaseRedisImpl implements ITicketPool {

    @Override
    public List<String> get(int count) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
        }
        return list;
    }

    @Override
    public String getOne() {
        return  null;
    }

    @Override
    public List<String> add(long count) {

        return null;
    }

    @Override
    public long length() {
        return  1;
    }
}
