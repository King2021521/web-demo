package com.nd.me.config.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * Created by zxm on 2017/7/17 0017.
 */
public class UserJobListener implements JobExecutionListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    long startTime;
    long executionTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        logger.info("批处理任务开始执行");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        executionTime = System.currentTimeMillis() - startTime;
        logger.info("批处理任务执行结束:耗时{}s",executionTime/1000);
    }
}
