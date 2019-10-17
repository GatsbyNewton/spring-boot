package edu.wzm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@EnableScheduling
@SpringBootApplication
public class ShedLockByRedisApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ShedLockByRedisApplication.class);
    }
}
