package edu.wzm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class SchedulerCustomizer implements SchedulerFactoryBeanCustomizer {
    private static final Logger LOGGER = LogManager.getLogger(SchedulerCustomizer.class);

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {

    }
}
