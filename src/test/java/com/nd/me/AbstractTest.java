package com.nd.me;

import com.nd.me.config.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class,
        WebRedisConfig.class,
        WebSecurityConfig.class,
        WebMybatisConfig.class,
        WebMongoConfig.class,
        WebRabbitAmqpConfig.class,
        SpringBatchConfig.class,
        WebQuartzConfig.class,
        WebElasticsearchConfig.class,
        DubboConsumerConfig.class,
        DubboProviderConfig.class})
@Ignore
public class AbstractTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
