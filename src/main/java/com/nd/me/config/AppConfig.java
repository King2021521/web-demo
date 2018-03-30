package com.nd.me.config;

/**
 * Created by zmx on 2017/3/13 0013.
 */
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
@Configuration
@ComponentScan(basePackages = { "com.nd.me.component", "com.nd.me.service","com.nd.me.util"})
@PropertySource(value = {"classpath:application.properties"})
@EnableAsync
@EnableScheduling
public class AppConfig {
}
