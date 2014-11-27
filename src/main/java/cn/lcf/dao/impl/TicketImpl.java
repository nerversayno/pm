package cn.lcf.dao.impl;

import cn.lcf.dao.AbstractBaseRedisImpl;
import cn.lcf.dao.ITicket;
import cn.lcf.entity.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 15:53
 * Description:
 */
public class TicketImpl extends AbstractBaseRedisImpl implements ITicket {
    @Override
    public boolean save(Ticket ticket) {
        pipeline.lpush(TICKET, ticket.toRedisString());
        pipeline.sync();
        return false;
    }

    @Override
    public long save(List<Ticket> ticketList) {
        for(Ticket ticket : ticketList){
            pipeline.lpush(TICKET,ticket.toRedisString());
        }
        pipeline.sync();
        return ticketList.size();
    }
}
