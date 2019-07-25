package com.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class DemoApplicationTests {

    @Autowired
    private SpringProcessEngineConfiguration spec;

    @Test
    public void deploy() {

        /**获取流程引擎*/
        ProcessEngine processEngine = spec.buildProcessEngine();

        /**部署流程，RepositoryService与流程定义和部署对象相关*/
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("请假流程")
                .addClasspathResource("ApplyProcess.bpmn")
                .deploy();
        System.out.println("部署流程ID： " + deployment.getId());
        System.out.println("流程名称：" + deployment.getName());
    }

    @Test
    public void startProcessInstance() throws Exception {
        // 流程定义的Key
        String processDefinitionKey = "myProcess_1";

        /**获取流程引擎*/
        ProcessEngine processEngine = spec.buildProcessEngine();

        /**RuntimeService，执行管理，包括启动、推进、删除流程实例等操作*/
        /**使用流程定义的key启动流程实例，默认按照最新版本启动*/
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .startProcessInstanceByKey(processDefinitionKey);
        System.out.println("启动流程实例： " + processInstance.getId());
        System.out.println("流程定义ID： " + processInstance.getProcessDefinitionId());

    }

    @Test
    public void contextLoads() throws Exception{
        //查看bean是否注入或数据是否null
        //Assert.assertNotNull(...);
    }
}
