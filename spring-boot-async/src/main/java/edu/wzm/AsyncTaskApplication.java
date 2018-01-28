package edu.wzm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAsync
public class AsyncTaskApplication {
    public static void main(String[] args){
        SpringApplication.run(AsyncTaskApplication.class);
    }
}
