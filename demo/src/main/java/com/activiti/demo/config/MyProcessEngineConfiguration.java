package com.activiti.demo.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyProcessEngineConfiguration implements ProcessEngineConfigurationConfigurer {

    public static final Logger logger = LoggerFactory.getLogger(MyProcessEngineConfiguration.class );

    @Autowired
    private ActivitiDataSourceProperties dataSourceProperties;

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setJdbcDriver(dataSourceProperties.getDriverClassName());
        springProcessEngineConfiguration.setJdbcUrl(dataSourceProperties.getUrl());
        springProcessEngineConfiguration.setJdbcUsername(dataSourceProperties.getUsername());
        springProcessEngineConfiguration.setJdbcPassword(dataSourceProperties.getPassword());
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");

        //设置数据库连接大小
        springProcessEngineConfiguration.setJdbcMaxActiveConnections(100);
        springProcessEngineConfiguration.setAsyncExecutorActivate(false);
    }
}
