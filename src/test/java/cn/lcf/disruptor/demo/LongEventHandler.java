package cn.lcf.disruptor.demo;

import cn.lcf.dao.ITicketPool;
import com.lmax.disruptor.EventHandler;
import redis.clients.jedis.Pipeline;

import java.util.UUID;

public class LongEventHandler implements EventHandler<LongEvent>
{
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
//        System.out.println("Event: " + event);
        Pipeline pipeline = event.getPipeline();
        pipeline.lpush(ITicketPool.TICKET_POOL, UUID.randomUUID().toString());
        pipeline.sync();
    }
}