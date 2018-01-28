package edu.wzm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by gatsbynewton on 2018/1/28.
 */
@Component
public class SyncScheduledTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(SyncScheduledTask.class);

    @Scheduled(cron = "0/5 * * * * ?")
    public void doWork1()throws Exception{
        LOGGER.info("同步定时任务：开始执行任务1");
        Thread.sleep(1000);
        Thread current = Thread.currentThread();
        LOGGER.info("定时任务1: " + current.getId() + ", name:" + current.getName());
        LOGGER.info("同步定时任务：完成执行任务1");
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void doWork2()throws Exception{
        LOGGER.info("同步定时任务：开始执行任务2");
        Thread.sleep(2000);
        Thread current = Thread.currentThread();
        LOGGER.info("定时任务2: " + current.getId() + ", name:" + current.getName());
        LOGGER.info("同步定时任务：完成执行任务2");
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void doWork3()throws Exception{
        LOGGER.info("同步定时任务：开始执行任务3");
        Thread.sleep(3000);
        Thread current = Thread.currentThread();
        LOGGER.info("定时任务3: " + current.getId() + ", name:" + current.getName());
        LOGGER.info("同步定时任务：完成执行任务3");
    }
}
