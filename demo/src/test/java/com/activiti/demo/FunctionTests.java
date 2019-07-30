package com.activiti.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class})
public class FunctionTests {

    @Autowired
    private SpringProcessEngineConfiguration spec;

    /**查询历史任务*/
    @Test
    public void queryHistoryTask() {

        ProcessEngine processEngine = spec.buildProcessEngine();

        HistoryService historyService = processEngine.getHistoryService();

        String assignee = "a";

        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .taskAssignee(assignee)
                .list();
        if(list != null && list.size() > 0){
            for (HistoricTaskInstance task : list) {
                System.out.println("任务ID： " + task.getId());
                System.out.println("流程实例ID： " + task.getProcessInstanceId());
                System.out.println("执行对象ID： " + task.getExecutionId());
            }
        }
        System.out.println("测试完成！");
    }

    @Test
    public void createProcessModel() {

        ProcessEngine processEngine = spec.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        String name = "流程模型创建测试";
        String key = "test_key";
        String description = "流程模型创建测试";

        Model model = repositoryService.newModel();
        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(description);

        repositoryService.saveModel(model);

        System.out.println("测试完成！");
    }

    @Test
    public void getProcessModel() {
        ProcessEngine processEngine = spec.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        String name = "20001";
        Model model = repositoryService.getModel(name);
        System.out.println(model.getId());

        System.out.println("测试完成！");
    }

    @Test
    public void deployProcessByModel() {
        ProcessEngine processEngine = spec.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        String id = "20001";

        repositoryService.addModelEditorSource(id, "ApplyProcess.bpmn".getBytes());

        Deployment deployment = repositoryService
                .createDeployment()
//                .addBytes(id, "ApplyProcess.bpmn".getBytes())
                .deploy();

        System.out.println("部署ID："+deployment.getId());
        System.out.println("部署时间："+deployment.getDeploymentTime());

        System.out.println("测试完成！");
    }
}
