package com.nd.me.config;

import com.nd.me.component.quartz.CronJob;
import com.nd.me.component.quartz.QuartzJob;
import org.quartz.CronExpression;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Created by zxm on 2017/7/20 0020.
 */
@Configuration
@PropertySource({"classpath:quartz.properties"})
public class WebQuartzConfig {
    @Resource
    private Environment env;

    /**
     * 配置调度的目标作业
     *
     * @return
     */
    @Bean(name = "jobDetail")
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(QuartzJob.class);
        jobDetail.setDurability(true);

        return jobDetail;
    }

    @Bean(name = "jobDetail2")
    public JobDetailFactoryBean jobDetailFactoryBean2() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(CronJob.class);
        jobDetail.setDurability(true);

        return jobDetail;
    }

    /**
     * 配置作业调度的触发方式
     *
     * @return
     */
    @Bean(name = "simple_trigger")
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean1(@Qualifier("jobDetail") JobDetail jobDetail) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        trigger.setStartDelay(Long.parseLong(env.getProperty("quartz.startdelay")));
        trigger.setRepeatInterval(Long.parseLong(env.getProperty("quartz.repeat")));
        return trigger;
    }

    @Bean(name = "cron_trigger")
    public CronTriggerBean cronTriggerFactoryBean(@Qualifier("jobDetail2") JobDetail jobDetail) throws ParseException{
        CronTriggerBean trigger = new CronTriggerBean();
        trigger.setJobDetail(jobDetail);
        trigger.setCronExpression("0/5 * * * * ?");
        return trigger;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("simple_trigger") Trigger simpleTrigger,@Qualifier("cron_trigger") Trigger cronTrigger) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(simpleTrigger,cronTrigger);
        schedulerFactoryBean.setAutoStartup(true);
        return schedulerFactoryBean;
    }
}
