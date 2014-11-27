package cn.lcf.dao;

import cn.lcf.entity.Ticket;

import java.util.List;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 9:05
 * Description:
 */
public interface ITicket {
    String SERVER = "http://localhost:8082/";

    String ADD="cgp/add";
    String SELL="cgp/sell";
    String LENGTH="cgp/length";

    public String TICKET = "ticket";

    public boolean save(Ticket ticket);

   public long save(List<Ticket> ticketList);

}
