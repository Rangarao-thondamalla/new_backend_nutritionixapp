package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }



    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth/**").uri("http://localhost:8081"))
                .route("contactmessage-service", r -> r.path("/api/contact/**").uri("http://localhost:8082"))
                .route("nutritionix-service", r -> r.path("/api/food/**").uri("http://localhost:8083"))
                .route("favorite-service", r -> r.path("/api/favorites/**").uri("http://localhost:8084"))
                .build();
    }

}
