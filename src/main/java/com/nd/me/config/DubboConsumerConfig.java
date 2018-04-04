package com.nd.me.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.nd.me.service.dubbo.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

@Configuration
@PropertySource({"classpath:dubbo.properties"})
public class DubboConsumerConfig {
    @Resource
    private Environment env;

    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(env.getProperty("dubbo.provider.app.name"));
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setId(env.getProperty("dubbo.registry.id"));
        registryConfig.setAddress(env.getProperty("dubbo.registry.address"));
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(env.getProperty("dubbo.protocol.name"));
        protocolConfig.setPort(Integer.parseInt(env.getProperty("dubbo.protocol.port").trim()));
        return protocolConfig;
    }

    @Bean
    public ReferenceConfig referenceConfig(){
        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setInterface(IUserService.class);
        referenceConfig.setId("iUserServiceImpl");
        referenceConfig.setCheck(false);
        return referenceConfig;
    }
}
