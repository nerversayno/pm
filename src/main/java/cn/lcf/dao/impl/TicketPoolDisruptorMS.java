package cn.lcf.dao.impl;

import cn.lcf.controller.HelloController;
import cn.lcf.controller.TicketController;
import cn.lcf.dao.AbstractBaseMS;
import cn.lcf.dao.ITicketPool;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * User: lcf
 * Date: 2014/9/9
 * Time: 16:47
 * Description:
 */
public class TicketPoolDisruptorMS extends AbstractBaseMS implements ITicketPool {
    @Override
    public List<String> get(int count) {
        return null;
    }

    @Override
    public String getOne() {
        return String.valueOf(TicketController.atomicInteger.addAndGet(1));
    }

    @Override
    public List<String> add(long count) {
        return null;
    }

    @Override
    public long length() {
        return 10000000 - TicketController.atomicInteger.get();
    }
}
