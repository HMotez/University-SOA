package com.univ.soa.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    // Public endpoints that do NOT require JWT
    private static final List<String> PUBLIC_ENDPOINTS = List.of(
            "/auth/signin",
            "/auth/register"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        // ============================================================
        // ✅ 1. BYPASS SOAP BILLING (ALL /billing/ws/**)
        // ============================================================
        if (path.startsWith("/billing/ws")) {
            System.out.println("GATEWAY: SOAP Billing request allowed → " + path);
            return chain.filter(exchange);
        }

        // ============================================================
        // ✅ 2. BYPASS PUBLIC AUTH ENDPOINTS
        // ============================================================
        for (String publicPath : PUBLIC_ENDPOINTS) {
            if (path.startsWith(publicPath)) {
                System.out.println("GATEWAY: Public endpoint → allowed: " + path);
                return chain.filter(exchange);
            }
        }

        // ============================================================
        // 🔒 3. VERIFY AUTHORIZATION HEADER EXISTS
        // ============================================================
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null) {
            return unauthorized(exchange, "Missing Authorization header");
        }

        if (!authHeader.startsWith("Bearer ")) {
            return unauthorized(exchange, "Authorization header must be in format: Bearer <token>");
        }

        // ============================================================
        // 🔒 4. EXTRACT TOKEN
        // ============================================================
        String token = authHeader.substring(7);

        // ============================================================
        // 🔒 5. VALIDATE TOKEN
        // ============================================================
        if (!jwtUtil.isTokenValid(token)) {
            return unauthorized(exchange, "Invalid or expired JWT token");
        }

        // ============================================================
        // ✅ 6. TOKEN VALID → CONTINUE REQUEST
        // ============================================================
        System.out.println("GATEWAY: Valid JWT → " + path);
        return chain.filter(exchange);
    }

    // ============================================================
    // ❌ RETURN 401 RESPONSE HELPERS
    // ============================================================
    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        System.err.println("UNAUTHORIZED ACCESS: " + message);
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
