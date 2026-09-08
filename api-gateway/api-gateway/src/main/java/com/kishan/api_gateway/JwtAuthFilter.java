package com.kishan.api_gateway; // ⚠️ match your actual package

import com.kishan.api_gateway.JwtUtil;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public class JwtAuthFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        String authHeader = request.headers().firstHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ServerResponse.status(401).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // strip "Bearer "

        if (!jwtUtil.isTokenValid(token)) {
            return ServerResponse.status(401).body("Invalid or expired token");
        }

        return next.handle(request); // token valid → let it through to User Service
    }
}