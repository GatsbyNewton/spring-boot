package edu.wzm.controller;

import edu.wzm.bo.QuartzJobBo;
import edu.wzm.job.MyJob;
import edu.wzm.manager.QuartzManagerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuartzController {
    private static final Logger LOGGER = LogManager.getLogger(QuartzController.class);

    @Autowired
    private QuartzManagerImpl quartzManager;

    private final String SUCCESS = "success";
    private final String FAILURE = "failure";

    @RequestMapping(value = "/add")
    public Object add(@RequestParam("name") String name, @RequestParam("sec") int sec){
        try {
            String cron = String.format("0/%d * * * * ?", sec);
            quartzManager.setScheduler(StdSchedulerFactory.getDefaultScheduler());
            quartzManager.addJob(new QuartzJobBo(name, name, name, name, cron, MyJob.class));

            return SUCCESS;
        } catch (SchedulerException se){
            LOGGER.error("Fail to add", se);
            return FAILURE;
        }
    }

    @RequestMapping(value = "/del")
    public Object delete(@RequestParam("name") String name, @RequestParam("sec") int sec){
        try {
            String cron = String.format("0/%d * * * * ?", sec);
            quartzManager.setScheduler(StdSchedulerFactory.getDefaultScheduler());
            quartzManager.deleteJob(new QuartzJobBo(name, name, name, name, cron, MyJob.class));

            return SUCCESS;
        } catch (SchedulerException se){
            LOGGER.error("Fail to delete", se);
            return FAILURE;
        }
    }

    @RequestMapping(value = "/modify")
    public Object modify(@RequestParam("name") String name, @RequestParam("sec") int sec){
        try {
            String cron = String.format("0/%d * * * * ?", sec);
            quartzManager.setScheduler(StdSchedulerFactory.getDefaultScheduler());
            quartzManager.modifyJob(new QuartzJobBo(name, name, name, name, cron, MyJob.class));

            return SUCCESS;
        } catch (SchedulerException se){
            LOGGER.error("Fail to modify", se);
            return FAILURE;
        }
    }

    @RequestMapping(value = "/pause")
    public Object pause(@RequestParam("name") String name){
        try {
            quartzManager.setScheduler(StdSchedulerFactory.getDefaultScheduler());
            quartzManager.pauseJob(name, name);

            return SUCCESS;
        }catch (SchedulerException se){
            LOGGER.error("Fail to pause", se);
            return FAILURE;
        }
    }

    @RequestMapping(value = "/resume")
    public Object resume(@RequestParam("name") String name){
        try {
            quartzManager.setScheduler(StdSchedulerFactory.getDefaultScheduler());
            quartzManager.resumeJob(name, name);

            return SUCCESS;
        }catch (SchedulerException se){
            LOGGER.error("Fail to resume", se);
            return FAILURE;
        }
    }
}
