package cn.lcf.disruptor.demo;

import redis.clients.jedis.Pipeline;

public class LongEvent {
    private Pipeline pipeline;

    private long value;

    public LongEvent(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

}