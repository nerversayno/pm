package cn.lcf.jedis;

import cn.lcf.dao.ITicketPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.UUID;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 14:47
 * Description:
 */
public class HelloJedis {
    Jedis jedis = new Jedis("localhost");

    Pipeline pipeline = jedis.pipelined();
    private long COUNT = 1000000;
    private long TIMES = 1;

    private long start;

    @Before
   public void before(){
       start = System.currentTimeMillis();
        System.out.println("start at -------" + start);
    }
    @After
    public void after(){
        long time = System.currentTimeMillis() -start;
        System.out.println("处理  "+COUNT * TIMES/10000 +" 万 耗时 : " + (double)time/(double)1000 +" 秒 ");
    }

    @Test
    public void testAdd(){

       for(int i=0;i <TIMES;i++){
           for (int j=0; j< COUNT;j++){
               pipeline.lpush(ITicketPool.TICKET_POOL, UUID.randomUUID().toString());
               pipeline.sync();
           }

       }
   }

    @Test
    public void testPop(){
        for(int i=0;i <TIMES;i++){
            for (int j=0; j< COUNT;j++){
                pipeline.rpop(ITicketPool.TICKET_POOL);
            }
            pipeline.sync();
        }
    }
}
