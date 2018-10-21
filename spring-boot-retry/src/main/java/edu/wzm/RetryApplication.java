package edu.wzm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author GatsbyNewton
 * @description Spring Boot请求重试机制，https://www.jianshu.com/p/314059943f1c
 */
@EnableRetry
@SpringBootApplication
public class RetryApplication {
    public static void main( String[] args ){
        SpringApplication.run(RetryApplication.class, args);
    }
}
