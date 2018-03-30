package com.nd.me.config;

import com.nd.me.config.listener.UserJobListener;
import com.nd.me.dao.mongo.repositories.UserDomain;
import com.nd.me.entity.User;
import com.nd.me.service.batch.UserMongoItemReader;
import com.nd.me.service.batch.UserMongoItemWriter;
import com.nd.me.service.batch.UserProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zxm on 2017/7/17 0017.
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Bean
    public JobRepository jobRepository() throws Exception{
        MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean = new MapJobRepositoryFactoryBean();
        mapJobRepositoryFactoryBean.setTransactionManager(transactionManager());
        return mapJobRepositoryFactoryBean.getObject();
    }

    @Bean
    public ResourcelessTransactionManager transactionManager(){
        return new ResourcelessTransactionManager();
    }

    @Bean
    public SimpleJobLauncher jobLauncher() throws Exception{
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepository());
        return simpleJobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step step1){
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1)
                .end()
                .listener(userJobListener())
                .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<UserDomain> reader, ItemWriter<User> writer, ItemProcessor<UserDomain,User> processor){
        return stepBuilderFactory.get("step1")
                .<UserDomain,User>chunk(5000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<UserDomain> reader() throws Exception{
        UserMongoItemReader reader = new UserMongoItemReader();
        return reader;
    }

    @Bean
    public ItemProcessor<UserDomain,User> processor(){
        return new UserProcessor();
    }

    @Bean
    public ItemWriter<User> writer(){
        return new UserMongoItemWriter();
    }

    @Bean
    public UserJobListener userJobListener(){
        return new UserJobListener();
    }
}
