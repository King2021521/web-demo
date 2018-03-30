package com.nd.me;

import com.nd.me.config.AppConfig;
import com.nd.me.config.WebRedisConfig;
import com.nd.me.config.WebMvcConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, WebMvcConfig.class, WebRedisConfig.class})
@Ignore
public class AbstractTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
