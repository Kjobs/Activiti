package com.activiti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class DemoApplicationTests {

    @Autowired
    private SpringProcessEngineConfiguration spec;

    /**
     * 部署流程
     */
    @Test
    public void deploy() {

        /**获取流程引擎*/
        ProcessEngine processEngine = spec.buildProcessEngine();

        /**RepositoryService，与流程定义和部署对象相关*/
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("请假流程")
                .addClasspathResource("ApplyProcess.bpmn")
                .deploy();

        // 打印测试
        System.out.println("部署流程ID： " + deployment.getId());
        System.out.println("流程名称：" + deployment.getName());
    }

    /**
     * 启动流程实例
     */
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

        // 打印测试
        System.out.println("启动流程实例： " + processInstance.getId());
        System.out.println("流程定义ID： " + processInstance.getProcessDefinitionId());

    }

    /**查看个人任务*/
    @Test
    public void queryPersonalTask() {
        // 任务办理者
        String assignee = "a";

        /**获取流程引擎*/
        ProcessEngine processEngine = spec.buildProcessEngine();

        /**查询任务列表*/
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();

        // 打印测试
        for(Task task : tasks) {
            System.out.println("任务ID: " + task.getId());
            System.out.println("任务名称: " + task.getName());
            System.out.println("流程实例ID: " + task.getProcessInstanceId());
            System.out.println("执行对象ID: " + task.getExecutionId());
            System.out.println("--------------------------------------");
        }
    }

    /**完成个人任务*/
    @Test
    public void completeTask() throws Exception {

        /**获取流程引擎*/
        ProcessEngine processEngine = spec.buildProcessEngine();

        // 已创建的个人任务ID
        String taskId = "2505";

        /**根据个人任务ID完成任务*/
        processEngine.getTaskService().complete(taskId);

        System.out.println("完成任务ID");
    }

    @Test
    public void contextLoads() throws Exception{
        //查看bean是否注入或数据是否null
        //Assert.assertNotNull(...);
    }
}
