package com.nd.me.component;

import com.alibaba.fastjson.JSONObject;
import com.nd.me.AbstractTest;
import com.nd.me.component.http.HttpTemplate;
import com.nd.me.component.http.RestHttpClient;
import com.nd.me.component.redis.RedisClientTemplate;

import com.nd.me.dao.mongo.repositories.UserDomain;
import com.nd.me.dao.mongo.repositories.UserRepository;
import com.nd.me.service.UserInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
public class RedisClientTemplateTest extends AbstractTest {
    @Autowired
    private RedisClientTemplate client;

    /*@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;*/

    @Autowired
    private Router router;

    @Autowired
    private UserRepository userRepository;

    private static Logger mongologger = LogManager.getLogger("mongologger");

    @Test
    public void test2() {
        UserInfoService service = router.getService(1);
        System.out.println(service);
    }


    @Test
    public void test3Mongo() {
        for (int i = 0; i < 10; i++) {
            UserDomain domain = new UserDomain();
            domain.setName("zxm"+i);
            domain.setAge("20"+i);
            domain.setPubDate(new Date());

            userRepository.insert(domain);
        }
    }

    @Test
    public void test4Log4j2(){
        for(int i=0;i<100;i++){
            mongologger.info("{\"aa\":123}");
        }
    }
}
