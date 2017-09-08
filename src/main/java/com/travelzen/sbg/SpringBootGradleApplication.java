package com.travelzen.sbg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.travelzen.sbg"})
public class SpringBootGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGradleApplication.class, args);
    }
}