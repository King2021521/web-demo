package com.nd.me.component.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by zxm on 2017/7/20 0020.
 */
public class QuartzJob extends QuartzJobBean{
    private static Logger logger = LoggerFactory.getLogger(QuartzJob.class);

    private static int count =0;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("quartz任务正在执行，第{}次调用",++count);
    }
}
