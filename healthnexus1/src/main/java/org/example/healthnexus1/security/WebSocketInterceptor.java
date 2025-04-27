package org.example.healthnexus1.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.http.HttpHeaders;
import java.util.Map;

public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
    private final JwtUtil jwtUtil;

    // Constructor for injecting JwtUtil
    public WebSocketInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // Extract token from the query string
        String token = extractToken(request);

        // Validate the token using the JwtUtil class
        if (isValidToken(token)) {
            return super.beforeHandshake(request, response, wsHandler, attributes);
        } else {
            response.setStatusCode(HttpStatus.FORBIDDEN); // Forbidden if invalid token
            return false;
        }
    }

    private String extractToken(ServerHttpRequest request) {
        String uri = request.getURI().toString();
        // Extract token from the URI query parameter
        String[] queryParams = uri.split("\\?");
        if (queryParams.length > 1) {
            String[] tokenParam = queryParams[1].split("=");
            if (tokenParam.length == 2 && tokenParam[0].equals("token")) {
                return tokenParam[1];
            }
        }
        return null; // Token not found
    }

    private boolean isValidToken(String token) {
        if (token == null) {
            return false; // Token is missing
        }
        return jwtUtil.validateToken(token); // Use the JwtUtil class to validate the token
    }
}
