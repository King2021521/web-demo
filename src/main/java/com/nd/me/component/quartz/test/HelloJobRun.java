package com.nd.me.component.quartz.test;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.job.builder.JobBuilder;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class HelloJobRun {
    private static final Logger logger = LoggerFactory.getLogger(HelloJobRun.class);

    public static void main(String[] args){
        /*try{
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            Date runTime = DateBuilder.evenMinuteDate(new Date());

            // 通过过JobDetail封装HelloJob，同时指定Job在Scheduler中所属组及名称，这里，组名为group1，而名称为job1。
            JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1","group1").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1").startAt(runTime).build();
            scheduler.scheduleJob(job,trigger);
            scheduler.start();

            try {
                //当前线程等待65秒
                Thread.sleep(10L * 1000L);
            } catch (Exception e) {

            }

            scheduler.shutdown(true);
            logger.error("结束运行。。。。");
        }catch(Exception e){
            logger.error(e.getMessage());
        }*/
    }
}
