package org.example.healthnexus1.dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;
import org.example.healthnexus1.security.JwtUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class SignalingHandler extends TextWebSocketHandler {

    private final JwtUtil jwtUtil; // JwtUtil injected via constructor

    private Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();

    @Autowired // Constructor-based injection
    public SignalingHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String token = session.getUri().getQuery().replace("token=", "");
        String userEmail = null;

        if (token != null && jwtUtil.validateToken(token)) {
            userEmail = jwtUtil.extractEmail(token);
            userSessions.put(userEmail, session);
            System.out.println("User connected: " + userEmail + " with session: " + session.getId());
        } else {
            session.close(CloseStatus.BAD_DATA);
            System.out.println("Invalid token or no token provided. Connection closed.");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("Received message: " + message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userSessions.entrySet().removeIf(entry -> entry.getValue().equals(session));
        System.out.println("Session closed: " + session.getId());
    }
}

