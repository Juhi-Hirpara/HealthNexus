package org.example.healthnexus1.config;

import org.example.healthnexus1.dto.SignalingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.config.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final SignalingHandler signalingHandler;

    @Autowired
    public WebSocketConfig(SignalingHandler signalingHandler) {
        this.signalingHandler = signalingHandler; // Inject SignalingHandler
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // Use the Spring-managed SignalingHandler instance
        registry.addHandler(signalingHandler, "/ws")
                .setAllowedOrigins("http://localhost:3000");
    }
}

