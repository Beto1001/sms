package com.fullsoft.sms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmsRequest {

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe contener 10 dígitos")
    private String telefono;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(min = 1, max = 50, message = "El mensaje debe tener entre 1 y 160 caracteres")
    private String mensaje;

}