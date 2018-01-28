package edu.wzm.controller;

import edu.wzm.service.ExecutorAsyncTaskService;
import edu.wzm.service.SimpleAsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * Created by gatsbynewton on 2018/1/28.
 */
@RestController
@RequestMapping("async")
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private SimpleAsyncTaskService simpleAsyncTaskService;

    @Autowired
    private ExecutorAsyncTaskService executorAsyncTaskService;

    @RequestMapping(value = "/simple_task", method = RequestMethod.GET)
    public @ResponseBody String simpleAsyncTask(){
        try {
            Future<String> task1 = simpleAsyncTaskService.doWork1();
            Future<String> task2 = simpleAsyncTaskService.doWork2();
            Future<String> task3 = simpleAsyncTaskService.doWork3();

            while(true) {
                if(task1.isDone() && task2.isDone() && task3.isDone()) {
                    break;
                }
                Thread.sleep(3000);
            }

            return task1.get();
        }
        catch (Exception e){
            LOGGER.error("Exception", e);
        }

        return "ERROR";
    }

    @RequestMapping(value = "/executor_task", method = RequestMethod.GET)
    public @ResponseBody String executorAsyncTask(){
        try {
            Future<String> task1 = executorAsyncTaskService.doWork1();
            Future<String> task2 = executorAsyncTaskService.doWork2();

            while(true) {
                if(task1.isDone() && task2.isDone()) {
                    break;
                }
                Thread.sleep(3000);
            }

            return task1.get();
        }
        catch (Exception e){
            LOGGER.error("Exception", e);
        }

        return "ERROR";
    }
}
