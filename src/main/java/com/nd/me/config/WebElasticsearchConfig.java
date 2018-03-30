package com.nd.me.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2017/8/7 0007.
 */
@Configuration
@PropertySource(value = {"classpath:elasticsearch.properties"})
public class WebElasticsearchConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Client getClient() throws Exception{
        Client client =null;
        try{
            client = TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9200));
        }catch(UnknownHostException e){
            logger.error(e.getMessage());
        }
        return client;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws Exception{
        ElasticsearchTemplate template = new ElasticsearchTemplate(getClient());
        return template;
    }
}
