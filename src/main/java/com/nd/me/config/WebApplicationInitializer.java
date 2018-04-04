package com.nd.me.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class,
                WebRedisConfig.class,
                WebSecurityConfig.class,
                WebMybatisConfig.class,
                WebMongoConfig.class,
                WebRabbitAmqpConfig.class,
                SpringBatchConfig.class,
                WebQuartzConfig.class,
                WebElasticsearchConfig.class,
                DubboConsumerConfig.class,
                DubboProviderConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {

        return new String[]{"/"};
    }

}