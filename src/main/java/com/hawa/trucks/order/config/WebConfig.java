package com.hawa.trucks.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
//                        .allowedOrigins("http://localhost:4200", "http://bookstore0324.s3-website.us-east-2.amazonaws.com")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .exposedHeaders("*");
            }
        };
    }

}