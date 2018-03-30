package com.nd.me.component.rabbitmq;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nd.me.dao.mongo.repositories.UserDomain;

@Component
public class SendMessageClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private AmqpTemplate amqpTemplate;
    
    //@Scheduled(fixedRate=10*1000)
    public void sendMessage(){
        UserDomain domain = new UserDomain();
        domain.setName("zxm");
        domain.setAge("24");
        domain.setPubDate(new Date());
        amqpTemplate.convertAndSend(domain);
        logger.info("send success!");
    }
}
