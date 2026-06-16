package com.fullsoft.sms.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final SmsSocketHandler smsSocketHandler;

    public WebSocketConfig(SmsSocketHandler smsSocketHandler) {
        this.smsSocketHandler = smsSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(
            WebSocketHandlerRegistry registry) {

        registry.addHandler(
                smsSocketHandler,
                "/sms-socket")
                .setAllowedOrigins("*");
    }
    
}