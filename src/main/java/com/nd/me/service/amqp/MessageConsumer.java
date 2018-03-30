package com.nd.me.service.amqp;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.nd.me.dao.mongo.repositories.UserDomain;
import com.nd.me.dao.mongo.repositories.UserRepository;
@Service
public class MessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private UserRepository repo;
    
    //@RabbitListener(containerFactory = "containerFactory", admin = "rabbitAdmin", bindings = @QueueBinding(value = @Queue(value = "${rabbit.amqp.queue.name}", durable = "${rabbit.amqp.queue.durable}"), exchange = @Exchange(value = "${rabbit.amqp.exchange.name}", type = "${rabbit.amqp.exchange.type}", durable = "${rabbit.amqp.exchange.durable}"), key = "${rabbit.amqp.exchange.key}"))
    public void process(UserDomain domain) {
        Assert.notNull(domain);
        
        logger.info("message array!!!content,name:{},age:{},pubDate:{}",domain.getName(),domain.getAge(),domain.getPubDate());
        repo.save(domain);
    }
}
