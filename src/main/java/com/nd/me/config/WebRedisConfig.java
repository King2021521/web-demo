package com.nd.me.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

@Configuration
@PropertySource({"classpath:redis.properties"})
public class WebRedisConfig {
    @Resource
    private Environment env;
    
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(Integer.parseInt(env.getProperty("redis.maxIdle").trim()));
        poolConfig.setMaxTotal(Integer.parseInt(env.getProperty("redis.maxTotal").trim()));
        poolConfig.setMaxWaitMillis(Long.parseLong(env.getProperty("redis.maxWaitMillis").trim()));
        poolConfig.setTestOnReturn(Boolean.parseBoolean(env.getProperty("redis.testOnReturn").trim()));
        poolConfig.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("redis.testWhileIdle").trim()));
        poolConfig.setTestOnBorrow(true);
        return poolConfig;
    }

    @Bean
    public ShardedJedisPool shardedJedisPool(){
        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        JedisShardInfo jedisShardInfo = new JedisShardInfo(
                env.getProperty("redis.host").trim(), 
                Integer.parseInt(env.getProperty("redis.port").trim()), 
                Integer.parseInt(env.getProperty("redis.timeout").trim()), 
                env.getProperty("redis.weight").trim()) ;
        list.add(jedisShardInfo);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig(), list);
        return shardedJedisPool;
    }
}
