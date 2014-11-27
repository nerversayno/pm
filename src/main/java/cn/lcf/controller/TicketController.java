package cn.lcf.controller;

import cn.lcf.dao.ITicket;
import cn.lcf.dao.ITicketPool;
import cn.lcf.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 8:52
 * Description:
 */
public class TicketController {

   public static long CURRENT_NO=0;

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Autowired
    ITicket ticketDao;

    @Autowired
    ITicketPool ticketPool;

    @ResponseBody
    @RequestMapping(value = "/cgp/sell2")
    public List<Ticket> sell2(
            @RequestParam(required = true) int client,
            @RequestParam(required = false, defaultValue = "1") int number) {
        long start = System.currentTimeMillis();
        long count = number / ITicketPool.PIECE_NUMBER;
        long left = number % ITicketPool.PIECE_NUMBER;
        List<Ticket> tickets = new ArrayList<Ticket>();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                tickets = localSell(ITicketPool.PIECE_NUMBER,client);
            }
        }
        if (left > 0) {
            tickets =  localSell(left,client);
        }
        long time = System.currentTimeMillis() - start;
        System.out.println(" 卖出 " + number + " 张票 ， 耗时 " + (double) time / (double) 1000 + "秒");
        return tickets;
    }

    @ResponseBody
    @RequestMapping(value = "/cgp/sell")
    public String sell(
            @RequestParam(required = true) int client,
            @RequestParam(required = false, defaultValue = "1") int number){
        String  guid = ticketPool.getOne();
        if(guid == null ){
           throw  new RuntimeException("sell over");
        }
        Ticket ticket = new Ticket();
        ticket.setClient(client);
        ticket.setGuid(guid);
        ticket.setSold(true);
        ticketDao.save(ticket);
       return guid;
    }

    @ResponseBody
    @RequestMapping(value = "/cgp/length")
    public long length() {
        return ticketPool.length();
    }

    @ResponseBody
    @RequestMapping(value = "/cgp/add")
    public List<String> add(@RequestParam(required = false, defaultValue = "24") long count) {
        long start = System.currentTimeMillis();
        List<String> list = ticketPool.add(count);
        long time = System.currentTimeMillis() - start;
        System.out.println(" 添加 " + count + " 条数据到 ticketPool 耗时 " + (double) time / (double) 1000 + "秒");
        return list;
    }

    private synchronized List<Ticket> localSell(long num,int client){
        List<Ticket> tickets = new ArrayList<Ticket>();
        List<String> list = ticketPool.get((int) num);
        tickets = new ArrayList<Ticket>();
        for (int k = 0; k < num; k++) {
            Ticket ticket = new Ticket();
            ticket.setClient(client);
            ticket.setGuid(list.get(k));
            ticket.setSold(true);
            tickets.add(ticket);
        }
        ticketDao.save(tickets);
        return tickets;
    }
}
