package edu.wzm;

import edu.wzm.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaRepositories(value = "edu.wzm.repository")
public class App{

    @Autowired
    CityRepository cityRepository;

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }
}
