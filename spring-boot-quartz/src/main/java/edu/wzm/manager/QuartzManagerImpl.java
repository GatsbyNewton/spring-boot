package edu.wzm.manager;

import edu.wzm.bo.QuartzJobBo;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.stereotype.Component;

@Data
@Component
public class QuartzManagerImpl implements QuartzManager{
    private static final Logger LOGGER = LogManager.getLogger(QuartzManagerImpl.class);

    private Scheduler scheduler;

    /**
     * 新建任务
     * @param jobBo
     * @throws SchedulerException
     */
    @Override
    public void addJob(QuartzJobBo jobBo) throws SchedulerException{
        if (scheduler != null && scheduler.isShutdown()){
            scheduler.start();
        }

        JobDetail jobDetail = JobBuilder.newJob(jobBo.getJobClazz())
                .withIdentity(jobBo.getJobName(), jobBo.getJobGroupName())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobBo.getTriggerName(), jobBo.getTriggerGroupName())
                .withSchedule(CronScheduleBuilder.cronSchedule(jobBo.getCronExp()))
                .startNow()
                .build();

        scheduler.scheduleJob(jobDetail, trigger);

        if (!scheduler.isShutdown()){
            scheduler.start();
        }
    }

    /**
     * 删除任务
     * @param jobBo
     * @throws SchedulerException
     */
    @Override
    public void deleteJob(QuartzJobBo jobBo) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobBo.getTriggerName(), jobBo.getTriggerGroupName());
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);

        scheduler.deleteJob(JobKey.jobKey(jobBo.getJobName(), jobBo.getJobGroupName()));
    }

    /**
     * 更新任务
     * @param jobBo
     * @throws SchedulerException
     */
    @Override
    public void modifyJob(QuartzJobBo jobBo) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobBo.getTriggerName(), jobBo.getTriggerGroupName());
        CronTrigger oldTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);

        LOGGER.info("old exp={}, new exp={}", oldTrigger.getCronExpression(), jobBo.getCronExp());
        if (oldTrigger != null && !oldTrigger.getCronExpression().equalsIgnoreCase(jobBo.getCronExp())){
            Trigger trigger = oldTrigger.getTriggerBuilder()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobBo.getCronExp()))
                    .build();

            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 暂停任务
     * @param jobName
     * @param jobGroupName
     * @throws SchedulerException
     */
    @Override
    public void pauseJob(String jobName, String jobGroupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
    }

    /**
     * 恢复任务
     * @param jobName
     * @param jobGroupName
     * @throws SchedulerException
     */
    @Override
    public void resumeJob(String jobName, String jobGroupName) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
    }
}






