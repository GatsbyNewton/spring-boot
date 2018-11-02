package edu.wzm;

import edu.wzm.intercepter.CustomPageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfiguration {

    @Bean
    public CustomPageInterceptor customPageInterceptor(){
        return new CustomPageInterceptor();
    }
}
