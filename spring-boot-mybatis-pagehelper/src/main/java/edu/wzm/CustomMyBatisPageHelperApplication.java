package edu.wzm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("edu.wzm.dao")
public class CustomMyBatisPageHelperApplication {
    public static void main( String[] args ){
        SpringApplication.run(CustomMyBatisPageHelperApplication.class, args);
    }
}
