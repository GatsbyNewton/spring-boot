package edu.wzm.bo;

import lombok.Data;
import org.quartz.Job;

@Data
public class QuartzJobBo {

    private String jobName;
    private String jobGroupName;
    private String triggerName;
    private String triggerGroupName;
    private String cronExp;
    private Class<? extends Job> jobClazz;

    public QuartzJobBo(String jobName,
                       String jobGroupName,
                       String triggerName,
                       String triggerGroupName,
                       String cronExp,
                       Class jobClazz) {
        this.jobName = jobName;
        this.jobGroupName = jobGroupName;
        this.triggerName = triggerName;
        this.triggerGroupName = triggerGroupName;
        this.cronExp = cronExp;
        this.jobClazz = jobClazz;
    }
}
