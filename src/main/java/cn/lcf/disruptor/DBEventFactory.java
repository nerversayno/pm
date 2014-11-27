package cn.lcf.disruptor;

import com.lmax.disruptor.EventFactory;
import org.springframework.stereotype.Component;

/**
 * User: lcf
 * Date: 2014/9/9
 * Time: 14:44
 * Description:
 */

public class DBEventFactory implements EventFactory<DBEvent> {
    @Override
    public DBEvent newInstance() {
        return new DBEvent();
    }
}
