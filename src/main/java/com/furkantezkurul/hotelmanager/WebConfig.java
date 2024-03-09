package com.furkantezkurul.hotelmanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000") // Allow only this origin to access
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Optionally restrict HTTP methods
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(3600); // Cache the preflight response for 1 hour
    }
}
