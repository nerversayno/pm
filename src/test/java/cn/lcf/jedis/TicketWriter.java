package cn.lcf.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;
import java.util.Queue;

/**
 * User: lcf
 * Date: 2014/8/22
 * Time: 11:22
 * Description:
 */
public class TicketWriter implements Runnable {

    Queue<String> queue;
    private int clientNumber;
    private Boolean flag;
    Jedis jedis = new Jedis("127.0.0.1");


    public TicketWriter(int clientNumber,Queue<String> queue,Boolean flag) {
        this.clientNumber = clientNumber;
        this.queue = queue;
        this.flag =flag;
    }

    @Override
    public void run() {
        boolean runFlag =true;
        while (runFlag){
            String str = queue.poll();
            if (str == null) {
                try {
                    if(!flag){
                        runFlag = flag;
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            Pipeline pipeline = jedis.pipelined();
//            long start = System.currentTimeMillis();
            pipeline.lpush("client"+clientNumber,str);
            pipeline.sync();
//            System.out.println(" write " + str + "use " + (System.currentTimeMillis() - start) + " 毫秒");
        }
    }
}
