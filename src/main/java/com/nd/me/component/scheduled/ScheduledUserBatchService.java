package com.nd.me.component.scheduled;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
@Service
public class ScheduledUserBatchService {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importJob;

    public JobParameters jobParameters;

    //@Scheduled(fixedDelay = 60)
    public void execute() throws Exception{
        jobParameters = new JobParametersBuilder().addLong("time",System.currentTimeMillis()).toJobParameters();
        jobLauncher.run(importJob,jobParameters);
    }
}
