package org.example;

import org.example.config.ApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {ApiConfig.class})
public class ShowPotAlarmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowPotAlarmApplication.class, args);
    }
}