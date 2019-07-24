package com.activiti.demo.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyProcessEngineConfiguration implements ProcessEngineConfigurationConfigurer {

    public static final Logger logger = LoggerFactory.getLogger(MyProcessEngineConfiguration.class );

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setJdbcDriver(driverClassName);
        springProcessEngineConfiguration.setJdbcUrl(url);
        springProcessEngineConfiguration.setJdbcUsername(username);
        springProcessEngineConfiguration.setJdbcPassword(password);
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");

        //设置数据库连接大小
        springProcessEngineConfiguration.setJdbcMaxActiveConnections(100);
        springProcessEngineConfiguration.setAsyncExecutorActivate(false);
    }
}
