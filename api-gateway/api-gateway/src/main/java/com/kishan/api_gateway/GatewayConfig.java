package com.kishan.api_gateway; // ⚠️ match your actual package

import com.kishan.api_gateway.JwtAuthFilter;
import com.kishan.api_gateway.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class GatewayConfig {

    private final JwtUtil jwtUtil;

    public GatewayConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return route("user-service")
                .route(RequestPredicates.path("/users/**"), http())
                .before(uri("http://localhost:8080"))
                .filter(new JwtAuthFilter(jwtUtil))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return route("product-service")
                .route(RequestPredicates.path("/products/**"), http())
                .before(uri("http://localhost:8082"))
                .filter(new JwtAuthFilter(jwtUtil))
                .build();
    }
}