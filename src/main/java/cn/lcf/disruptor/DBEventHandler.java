package cn.lcf.disruptor;


import cn.lcf.dao.AbstractBaseMS;
import com.lmax.disruptor.EventHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * User: lcf
 * Date: 2014/9/9
 * Time: 14:33
 * Description:
 */
public class DBEventHandler extends AbstractBaseMS implements EventHandler<DBEvent> {
    @Override
    public void onEvent(DBEvent event, long sequence, boolean endOfBatch) throws Exception {
        try {
            super.execute(event.getSql(),event.getArgs());
        } finally {
        }
    }
}
