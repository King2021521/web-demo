package com.nd.me.config;

import com.alibaba.druid.pool.DruidDataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import java.sql.SQLException;

@Configuration
@PropertySource("classpath:mybatis.properties")
@MapperScan("com.nd.me.dao.mysql")
@EnableTransactionManagement
public class WebMybatisConfig {

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(env.getProperty("druid.driverClassName").trim());
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("druid.initialSize").trim()));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("druid.minIdle").trim()));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("druid.maxActive").trim()));
        dataSource.setMaxWait(Long.parseLong(env.getProperty("druid.maxWait").trim()));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("druid.timeBetweenEvictionRunsMillis").trim()));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("druid.minEvictableIdleTimeMillis").trim()));
        dataSource.setValidationQuery(env.getProperty("druid.validationQuery").trim());
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("druid.testWhileIdle").trim()));
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("druid.testOnBorrow").trim()));
        dataSource.setTestOnReturn(Boolean.valueOf((env.getProperty("druid.testOnReturn").trim())));
        dataSource.setUrl(env.getProperty("druid.url").trim());
        dataSource.setUsername(env.getProperty("druid.username").trim());
        dataSource.setPassword(env.getProperty("druid.password").trim());

        try {
            dataSource.setFilters(env.getProperty("druid.filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
        org.apache.ibatis.mapping.Environment environment = new org.apache.ibatis.mapping.Environment(
                SqlSessionFactoryBean.class.getSimpleName(), transactionFactory, dataSource());
        configuration.setEnvironment(environment);
        configuration.setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBuilder.build(configuration);

    }
}