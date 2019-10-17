package edu.wzm.scheduler;

import net.javacrumbs.shedlock.core.SchedulerLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private static final Logger LOGGER = LogManager.getLogger(Scheduler.class);

    @Scheduled(cron = "*/10 * * * * ?")
    @SchedulerLock(name = "schedule-mysql", lockAtLeastForString = "PT1M", lockAtMostForString = "PT2M")
    public void scheduleByMySQL() {
        LOGGER.error("ShedLock by MySQL: {}", System.currentTimeMillis());
    }
}
