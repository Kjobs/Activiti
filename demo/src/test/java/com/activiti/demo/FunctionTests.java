package com.activiti.demo;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
