package com.example.taktreker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TakTrekerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakTrekerApplication.class, args);
    }

}
