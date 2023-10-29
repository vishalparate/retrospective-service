package com.example.retrospective.swagger;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
                .group("retrospective-service")
                .packagesToScan("com.example.retrospective.controller") // Specify the package to scan
                .build();
    }

}

