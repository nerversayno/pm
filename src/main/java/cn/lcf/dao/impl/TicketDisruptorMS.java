package cn.lcf.dao.impl;

import cn.lcf.dao.ITicket;
import cn.lcf.disruptor.DBEventDisruptor;
import cn.lcf.disruptor.DBEventProducer;
import cn.lcf.entity.Ticket;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: lcf
 * Date: 2014/9/9
 * Time: 15:05
 * Description:
 */
public class TicketDisruptorMS implements ITicket {

    @Resource
    DBEventProducer dbEventProducer;

    @Override
    public boolean save(Ticket ticket) {
        dbEventProducer.onData("insert into ticket(guid,client,sold) values(?,?,?)",ticket.getGuid(),ticket.getClient(),ticket.getSold());
        return false;
    }

    @Override
    public long save(List<Ticket> ticketList) {
        for(Ticket ticket : ticketList){
            dbEventProducer.onData("insert into ticket(guid,client,sold) values(?,?,?)",ticket.getGuid(),ticket.getClient(),ticket.getSold());
        }
        return 0;
    }
}
