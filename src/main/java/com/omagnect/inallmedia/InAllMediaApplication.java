package com.omagnect.inallmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EntityScan
@SpringBootApplication
public class InAllMediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(InAllMediaApplication.class, args);
    }

}
