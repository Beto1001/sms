package com.fullsoft.sms.config.websocket;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SmsSocketHandler extends TextWebSocketHandler {

    private WebSocketSession androidSession;

    private final Logger log = LoggerFactory.getLogger(SmsSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

        log.info("ANDROID CONECTADO");

        this.androidSession = session;
    }

    @Override
    public void afterConnectionClosed(
            WebSocketSession session,
            CloseStatus status) {

        log.info("ANDROID DESCONECTADO");

        this.androidSession = null;
    }

    public void enviarMensaje(String json)
            throws IOException {

        if (androidSession != null
                && androidSession.isOpen()) {

            androidSession.sendMessage(
                    new TextMessage(json));
        }
    }

}