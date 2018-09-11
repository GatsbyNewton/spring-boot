package edu.wzm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by gatsbynewton on 2017/10/8.
 */
@SpringBootApplication
@MapperScan("edu.wzm.dao")
public class MyBatisXmlApplication {
    public static void main(String[] args){
        SpringApplication.run(MyBatisXmlApplication.class);
    }
}
