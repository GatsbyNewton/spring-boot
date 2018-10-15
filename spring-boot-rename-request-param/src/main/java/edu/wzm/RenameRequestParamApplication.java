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
public class RenameRequestParamApplication {
    public static void main( String[] args ) {
        SpringApplication.run(RenameRequestParamApplication.class, args);
    }
}
