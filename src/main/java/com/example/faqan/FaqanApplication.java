package com.example.faqan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FaqanApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaqanApplication.class, args);
    }

}
