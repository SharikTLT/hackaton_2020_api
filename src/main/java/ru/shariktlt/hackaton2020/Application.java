package ru.shariktlt.hackaton2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.shariktlt.hackaton2020"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
