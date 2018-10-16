package edu.wzm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jimmy Wong
 * @description https://www.cnblogs.com/davidwang456/p/6781731.html
 */
@SpringBootApplication
@MapperScan(value = "edu.wzm.dao")
public class RenameRequestParamApplication {
    public static void main( String[] args ) {
        SpringApplication.run(RenameRequestParamApplication.class, args);
    }
}
