package com.fullsoft.sms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullsoft.sms.config.websocket.SmsSocketHandler;
import com.fullsoft.sms.dto.SmsRequest;

@Service
public class SmsService {

    private final ObjectMapper mapper;
    private final SmsSocketHandler socketHandler;

    private final Logger logger = LoggerFactory.getLogger(SmsService.class);

    public SmsService(ObjectMapper mapper, SmsSocketHandler socketHandler) {
        this.mapper = mapper;
        this.socketHandler = socketHandler;

    }

    @Async
    public void enviar(String telefono, String mensaje) {

        try {

            SmsRequest request = new SmsRequest(
                    telefono,
                    mensaje);

            String json = mapper.writeValueAsString(request);

            socketHandler.enviarMensaje(json);

        } catch (Exception e) {
            logger.error("Error:",e);
        }

    }

}