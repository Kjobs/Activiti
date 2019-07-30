package com.activiti.demo;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        // 解决spring boot 2.x版本与activiti的SecurityAutoConfiguration冲突问题
        exclude = {
                org.activiti.spring.boot.SecurityAutoConfiguration.class,
                SecurityAutoConfiguration.class
        }
)
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
