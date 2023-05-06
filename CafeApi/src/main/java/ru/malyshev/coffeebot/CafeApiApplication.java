package ru.malyshev.coffeebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CafeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeApiApplication.class, args);
    }
}