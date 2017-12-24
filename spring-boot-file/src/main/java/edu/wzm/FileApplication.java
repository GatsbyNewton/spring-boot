package edu.wzm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableJpaRepositories(value = "edu.wzm.repository")
public class FileApplication {
    public static void main( String[] args ) {
        SpringApplication.run(FileApplication.class);
    }
}
