package cn.lcf.dao.impl;

import cn.lcf.controller.TicketController;
import cn.lcf.dao.AbstractBaseMS;
import cn.lcf.dao.ITicket;
import cn.lcf.dao.ITicketPool;
import cn.lcf.disruptor.DBEventProducer;
import cn.lcf.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: lcf
 * Date: 2014/8/22
 * Time: 9:46
 * Description:
 */

public class TicketMS extends AbstractBaseMS implements ITicket {

    @Override
    public boolean save(Ticket ticket) {
        return super.execute("insert into ticket(guid,client,sold) values(?,?,?)",ticket.getGuid(),ticket.getClient(),ticket.getSold());
    }

    @Override
    public long save(List<Ticket> ticketList) {
        return 0;
    }
}
