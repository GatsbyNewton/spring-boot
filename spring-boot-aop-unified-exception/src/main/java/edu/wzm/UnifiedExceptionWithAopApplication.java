package edu.wzm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(value = "edu.wzm.dao")
public class UnifiedExceptionWithAopApplication {
    public static void main( String[] args ){
        SpringApplication.run(UnifiedExceptionWithAopApplication.class, args);
    }
}
