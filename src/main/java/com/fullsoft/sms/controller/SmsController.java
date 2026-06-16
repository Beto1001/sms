package com.fullsoft.sms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullsoft.sms.dto.SmsRequest;
import com.fullsoft.sms.dto.SmsResponse;
import com.fullsoft.sms.services.SmsService;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping
    public ResponseEntity<SmsResponse> enviar(@RequestBody SmsRequest request) {
        smsService.enviar(request.getTelefono(), request.getMensaje());
        return ResponseEntity.ok(new SmsResponse("Mensaje enviado correctamente"));
    }
    
}