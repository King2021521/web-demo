package com.nd.me.component.redis;

import java.util.List;
import java.util.Map;

import com.nd.me.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;

@Component
public class RedisClientTemplate {

    private static final Logger log = LoggerFactory.getLogger(RedisClientTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;
    
    @Autowired
    private SerializeUtil serializeUtil;
    
    public void disconnect() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        shardedJedis.disconnect();
    }
    
    /**
     * 设置单个值
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        String result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.set(key, value);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    /**
     * 获取单个值
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        try {
            result = shardedJedis.get(key);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 判断key是否存在
     * 
     * @param key
     * @return
     * @since
     */
    public Boolean exists(String key) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.exists(key);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 设置过期时间
     * 
     * @param key
     * @param unixTime
     * @return
     */
    public Long expire(String key, int seconds) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.expire(key, seconds);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 设置hash结构的field为value
     * 
     * @param key
     * @param field
     * @param value
     * @return
     * @since
     */
    public Long hset(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.hset(key, field, value);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 获取hash结构的field的value
     * 
     * @param key
     * @param field
     * @return
     * @since
     */
    public String hget(String key, String field) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.hget(key, field);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 不存在的情况下设置key的field为value
     * 
     * @param key
     * @param field
     * @param value
     * @return
     * @since
     */
    public Long hsetnx(String key, String field, String value) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.hsetnx(key, field, value);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    /**
     * hash缓存方式
     * 
     * @param key
     * @param hash
     * @return
     * @since
     */
    public String hmset(String key, Map<String, String> hash) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.hmset(key, hash);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }

    public List<String> hmget(String key, String... fields) {
        List<String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.hmget(key, fields);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 测试指定的field是否存在
     * 
     * @param key
     * @param field
     * @return
     * @since
     */
    public Boolean hexists(String key, String field) {
        Boolean result = false;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.hexists(key, field);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 删除指定的缓存
     * 
     * @param key
     * @return
     * @since
     */
    public Long del(String key) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.del(key);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 删除指定的field
     * 
     * @param key
     * @param field
     * @return
     * @since
     */
    public Long hdel(String key, String field) {
        Long result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.hdel(key, field);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    public Map<String, String> hgetAll(String key) {
        Map<String, String> result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        try {
            result = shardedJedis.hgetAll(key);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        } finally {
            redisDataSource.returnResource(shardedJedis);
        }
        return result;
    }
    
    /**
     * 缓存对象
     * 
     * @param key
     * @param value
     * @return
     * @since
     */
    public String setObject(String key,Object value){
        String serializeStr = null;
        try {
            serializeStr = serializeUtil.serialize(value);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return this.set(key, serializeStr);
    }
    
    /**
     * 获取缓存的对象
     * 
     * @param key
     * @return
     * @since
     */
    public <T> T getObject(String key,Class<T> T){
        String resultStr = this.get(key);
        try {
            return serializeUtil.deserialize(resultStr,T);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }
}
