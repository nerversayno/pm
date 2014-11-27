package cn.lcf.jedis;

import redis.clients.jedis.Jedis;

import java.util.TimerTask;

/**
 * User: lcf
 * Date: 2014/8/21
 * Time: 14:18
 * Description:
 */
public class TicketClientTest {
    public static void main(String[] args) {
//        new Thread(new TicketAdder(1,TicketAdder.MIN_COUNT *10)).start();
        new Thread(new TicketMonitor(2,0,20*60*1000)).start();
        for(int i=0; i<30; i++){
            new Thread(new TicketSeller(i, 1)).start();
//          new Thread(new TicketClientReader(i)).start();
        }
    }
}
