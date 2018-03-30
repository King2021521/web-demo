package com.nd.me.component.quartz.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by zxm on 2017/7/20 0020.
 */
public class HelloJob implements Job {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public HelloJob(){}

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("hello word!!!##########"+new Date());
    }
}
