package cn.lcf.disruptor.demo1;

import cn.lcf.disruptor.demo1.TradeTransaction;
import com.lmax.disruptor.EventHandler;

import java.util.UUID;


public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>{

	@Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
		this.onEvent(event);
	}

	public void onEvent(TradeTransaction event) throws Exception{
		event.setId(UUID.randomUUID().toString());
		System.out.println(event.getId());
	}

}