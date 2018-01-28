package edu.wzm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * Created by gatsbynewton on 2018/1/28.
 */
@Service
public class ExecutorAsyncTaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorAsyncTaskService.class);

    @Async("simpleExecutor")
    public Future<String> doWork1()throws Exception{
        LOGGER.info("自定义Executor异步任务1：开始执行任务1");
        Thread.sleep(1000);
        Thread current = Thread.currentThread();
        LOGGER.info("任务1: " + current.getId() + ", name:" + current.getName());
        LOGGER.info("自定义Executor异步任务1：完成执行任务1");

        return new AsyncResult<>("自定义Executor异步任务1：完成执行任务1");
    }

    @Async("asyncExecutor")
    public Future<String> doWork2()throws Exception{
        LOGGER.info("自定义Executor异步任务2：开始执行任务2");
        Thread.sleep(2000);
        Thread current = Thread.currentThread();
        LOGGER.info("任务2: " + current.getId() + ", name:" + current.getName());
        LOGGER.info("自定义Executor异步任务2：完成执行任务2");

        return new AsyncResult<>("自定义Executor异步任务2：完成执行任务2");
    }
}
