package com.rajeeb.cloudgateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public RouteLocator orderServiceRoute(RouteLocatorBuilder builder) {
        return builder.routes().build();
    }

    @Bean
    public RouteLocator retailOrderServiceCircuitBreaker(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/payment/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("paymentCircuitBreaker")
                                .setFallbackUri("forward:/paymentFallBack")))
                        .uri("lb://PAYMENT-SERVICE"))
                .route(p -> p.path("/order/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("orderCircuitBreaker")
                                .setFallbackUri("forward:/orderFallBack")))
                        .uri("lb://ORDER-SERVICE"))
                .route(p -> p.path("/auth/**")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("identityCircuitBreaker")
                                .setFallbackUri("forward:/identityFallBack")))
                        .uri("lb://IDENTITY-SERVICE"))
                .build();
    }
}
