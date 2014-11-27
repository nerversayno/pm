package cn.lcf.disruptor.demo2;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;


public class TradeTransactionInDBHandler implements WorkHandler<TradeTransaction>{

	@Override
    public void onEvent(TradeTransaction event) throws Exception {
		event.setId(UUID.randomUUID().toString());
		System.out.println(event.getId());
		
	}

}