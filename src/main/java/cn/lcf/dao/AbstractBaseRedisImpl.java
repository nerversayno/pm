package cn.lcf.dao;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * User: lcf
 * Date: 2014/8/20
 * Time: 15:35
 * Description:
 */
public abstract class AbstractBaseRedisImpl {
    public Jedis jedis = new Jedis("localhost");

    public Pipeline pipeline = jedis.pipelined();
}
