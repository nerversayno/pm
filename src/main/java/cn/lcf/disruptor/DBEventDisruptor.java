package cn.lcf.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * User: lcf
 * Date: 2014/9/9
 * Time: 14:43
 * Description:
 */
public class DBEventDisruptor {
    DBEventFactory factory;
    DBEventHandler handler;

    // Construct the Disruptor
    public Disruptor<DBEvent> disruptor;

    public DBEventDisruptor() {

    }

    public void disruptorStart() {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        disruptor = new Disruptor<>(factory, bufferSize, executor);
        // Connect the handler
        disruptor.handleEventsWith(handler);

        // Start the Disruptor, starts all threads running
        disruptor.start();
    }
}
