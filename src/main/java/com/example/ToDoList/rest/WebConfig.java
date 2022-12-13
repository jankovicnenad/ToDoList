package com.example.ToDoList.rest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
@Configuration
public class WebConfig {

    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/api")
                .allowedOrigins("192.168.0.105")
                .allowedMethods("POST", "GET", "PUT", "DELETE");
    }
}
