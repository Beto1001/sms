package com.fullsoft.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsRequest {

    private String telefono;
    private String mensaje;

}