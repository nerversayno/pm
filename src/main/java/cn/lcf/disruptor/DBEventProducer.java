package cn.lcf.disruptor;

import cn.lcf.dao.AbstractBaseMS;
import com.lmax.disruptor.RingBuffer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * User: lcf
 * Date: 2014/9/9
 * Time: 14:36
 * Description:
 */
public class DBEventProducer extends AbstractBaseMS {

    DBEventDisruptor dbEventDisruptor;

    private RingBuffer<DBEvent> ringBuffer;

    public DBEventProducer() {

    }


    public void initRingBuffer() {
        ringBuffer = dbEventDisruptor.disruptor.getRingBuffer();
    }

    public void onData(String sql, Object... args) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            DBEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            event.setArgs(args);
            event.setSql(sql);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
