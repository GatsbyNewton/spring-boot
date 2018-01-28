package edu.wzm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by gatsbynewton on 2018/1/28.
 */
@Service
public class SimpleAsyncTaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAsyncTaskService.class);

    @Async
    public Future<String> doWork1()throws Exception{
        LOGGER.info("异步任务1：开始执行任务1");
        Thread.sleep(1000);
        Thread current = Thread.currentThread();
        LOGGER.info("任务1: " + current.getId() + ", name:" + current.getName());
        LOGGER.info("异步任务1：完成执行任务1");

        return new AsyncResult<>("异步任务1：完成执行任务1");
    }

    @Async
    public Future<String> doWork2()throws Exception{
        LOGGER.info("异步任务2：开始执行任务2");
        Thread.sleep(2000);
        Thread current = Thread.currentThread();
        LOGGER.info("任务2: " + current.getId() + ", name:" + current.getName());
        LOGGER.info("异步任务2：完成执行任务2");

        return new AsyncResult<>("异步任务2：完成执行任务2");
    }

    @Async
    public Future<String> doWork3()throws Exception{
        LOGGER.info("异步任务3：开始执行任务3");
        Thread.sleep(3000);
        Thread current = Thread.currentThread();
        LOGGER.info("任务3: " + current.getId() + ", name:" + current.getName());
        LOGGER.info("异步任务3：完成执行任务3");

        return new AsyncResult<>("异步任务3：完成执行任务3");
    }
}
