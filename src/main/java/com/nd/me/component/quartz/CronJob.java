package com.nd.me.component.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Administrator on 2017/8/21 0021.
 */
public class CronJob extends QuartzJobBean{
    private static final Logger logger = LoggerFactory.getLogger(CronJob.class);
    private static int num = 0;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("cron方式调用。。。。"+ num++);
    }
}
