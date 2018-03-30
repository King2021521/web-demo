package com.nd.me.config;

import java.net.UnknownHostException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClientURI;

/**
 * mongoDb配置类
 * @author zxm
 */
@Configuration
@PropertySource({"classpath:mongo.properties"})
@EnableMongoRepositories(basePackages="com.nd.me.dao.mongo.repositories")
public class WebMongoConfig {
    private static final Logger log = LoggerFactory.getLogger(WebMongoConfig.class);
    
    @Resource
    private Environment env;
    
    @Bean
    public SimpleMongoDbFactory mongoDbFactory(){
        SimpleMongoDbFactory simpleMongoDbFactory = null;
        try {
            simpleMongoDbFactory = new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("mongo.uri")));
        } catch (UnknownHostException e) {
            log.error("无法连接mongo服务器",e);
        }
        return simpleMongoDbFactory;
    }
    
    @Bean
    public MongoMappingContext mappingContext(){
        MongoMappingContext context = new MongoMappingContext();
        return context;
    }
    
    /**
     * 去除_class字段	
     * @return
     */
    @Bean
    public DefaultMongoTypeMapper defaultMongoTypeMapper(){
        DefaultMongoTypeMapper mapper = new DefaultMongoTypeMapper(null);
        return mapper;
    }
    
    @Bean
    public MappingMongoConverter mappingMongoConverter(){
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory()), mappingContext());
        converter.setTypeMapper(defaultMongoTypeMapper());
        return converter;
    }
    
    /**
     * 操作mongodb	
     * <p>Create Time: 2017年6月1日   </p>
     */
    @Bean
    public MongoTemplate mongoTemplate(){
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
        return mongoTemplate;
    }
}
