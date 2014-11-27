package cn.lcf.disruptor.demo;

import com.lmax.disruptor.EventFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class LongEventFactory implements EventFactory<LongEvent>
{
    Pipeline pipeline = new Jedis("127.0.0.1").pipelined();

    public LongEvent newInstance()
    {
        return new LongEvent(pipeline);
    }
}