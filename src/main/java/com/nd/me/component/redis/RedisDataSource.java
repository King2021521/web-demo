package com.nd.me.component.redis;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {
    
    public abstract ShardedJedis getRedisClient();
    public void returnResource(ShardedJedis shardedJedis);
}
