package com.nd.me.component;

import com.alibaba.fastjson.JSONObject;
import com.nd.me.AbstractTest;
import com.nd.me.component.http.HttpTemplate;
import com.nd.me.component.http.RestHttpClient;
import com.nd.me.component.redis.RedisClientTemplate;

import com.nd.me.service.UserInfoService;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class RedisClientTemplateTest extends AbstractTest{
    @Autowired
    private RedisClientTemplate client;

    /*@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;*/

    @Autowired
    private Router router;

    @Test
    public void test(){
        HttpTemplate template = RestHttpClient.getClient();
        JSONObject result = template.getForObject("http://localhost:9200/blog/ariticle/2", JSONObject.class);
        System.out.println("-----"+result);
    }

    @Test
    public void test2(){
        UserInfoService service = router.getService(1);
        System.out.println(service);
    }
}
