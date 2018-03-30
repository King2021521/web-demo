package com.nd.me.config;

import javax.annotation.Resource;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@PropertySource({"classpath:amqp.properties"})
@EnableRabbit
public class WebRabbitAmqpConfig implements RabbitListenerConfigurer{
    @Resource
    private Environment env;
    
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(env.getProperty("rabbit.amqp.host").trim());
        connectionFactory.setPort(Integer.parseInt(env.getProperty("rabbit.amqp.port").trim()));
        connectionFactory.setUsername(env.getProperty("rabbit.amqp.username").trim());
        connectionFactory.setPassword(env.getProperty("rabbit.amqp.password").trim());
        return connectionFactory;
    }
    
    @Bean
    public Queue queue(){
        Queue queue = new Queue(env.getProperty("rabbit.amqp.queue.name"),Boolean.parseBoolean(env.getProperty("rabbit.amqp.queue.durable")));
        queue.setAdminsThatShouldDeclare(rabbitAdmin());
        queue.setShouldDeclare(true);
        return queue;
    }
    
    @Bean
    public FanoutExchange exchange(){
        FanoutExchange exchange = new FanoutExchange(env.getProperty("rabbit.amqp.exchange.name"),Boolean.parseBoolean(env.getProperty("rabbit.amqp.exchange.durable")),false);
        exchange.setAdminsThatShouldDeclare(rabbitAdmin());
        exchange.setShouldDeclare(true);
        return exchange;
    }
    
    @Bean
    public Binding rabbitBinding(){
        return new Binding(env.getProperty("rabbit.amqp.queue.name"), DestinationType.QUEUE, env.getProperty("rabbit.amqp.exchange.name"), env.getProperty("rabbit.amqp.exchange.key"), null);
    }
    
    @Bean
    public RabbitTemplate amqpTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setRetryTemplate(retryTemplate());
        rabbitTemplate.setExchange(env.getProperty("rabbit.amqp.exchange.name"));
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    
    @Bean
    public RetryTemplate retryTemplate(){
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(new ExponentialBackOffPolicy());
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy());
        return retryTemplate;
    }
    
    @Bean
    public RabbitAdmin rabbitAdmin(){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        return rabbitAdmin;
    }
    
    /**
     * 发送消息的json序列化	
     * @return
     */
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(){
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        return jackson2JsonMessageConverter;
    }
    
    @Bean
    public SimpleRabbitListenerContainerFactory containerFactory(){
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory());
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(10);
        return simpleRabbitListenerContainerFactory;
    }
    
    /**
     * 接收消息的json序列化	
     * @param registrar
     */
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(handlerMethodFactory());
    }
    
    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    @Bean
    public DefaultMessageHandlerMethodFactory handlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(jackson2Converter());
        return factory;
    }
}
