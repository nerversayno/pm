package cn.lcf.dao;

import java.util.List;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 9:17
 * Description:
 */
public interface ITicketPool {
    public String TICKET_POOL = "ticketPool";

    public long PIECE_NUMBER = 100000;

    public List<String> get(int count);

    public String getOne();

    public List<String> add(long count);

    public long length();


}
