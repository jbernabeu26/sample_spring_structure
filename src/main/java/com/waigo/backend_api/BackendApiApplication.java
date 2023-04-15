package com.waigo.backend_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Configuration



@SpringBootApplication
@ComponentScan(basePackages = {"com.waigo.backend_api.category"})
@EntityScan("com.waigo.backend_api.category.*")
@EnableJpaRepositories("com.waigo.backend_api.category")
public class BackendApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApiApplication.class, args);
    }

}
