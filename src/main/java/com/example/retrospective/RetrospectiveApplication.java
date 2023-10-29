package com.example.retrospective;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@Slf4j
public class RetrospectiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetrospectiveApplication.class, args);
        log.info("Retrospective application started.....");
    }
}
