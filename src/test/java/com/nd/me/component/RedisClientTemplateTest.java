package com.nd.me.component;

import com.nd.me.AbstractTest;
import com.nd.me.component.redis.RedisClientTemplate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class RedisClientTemplateTest extends AbstractTest{
    @Autowired
    private RedisClientTemplate client;

    @Test
    public void test(){
        client.set("name","zxm");
        System.out.println(client.get("name"));
    }
}
