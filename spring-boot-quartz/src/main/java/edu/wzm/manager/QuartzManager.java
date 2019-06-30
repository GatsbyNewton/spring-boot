package edu.wzm.manager;

import edu.wzm.bo.QuartzJobBo;
import org.quartz.SchedulerException;

public interface QuartzManager {

    void addJob(QuartzJobBo jobBo) throws SchedulerException;

    void deleteJob(QuartzJobBo jobBo) throws SchedulerException;

    void modifyJob(QuartzJobBo jobBo) throws SchedulerException;

    void pauseJob(String jobName, String jobGroupName) throws SchedulerException;

    void resumeJob(String jobName, String jobGroupName) throws SchedulerException;
}
