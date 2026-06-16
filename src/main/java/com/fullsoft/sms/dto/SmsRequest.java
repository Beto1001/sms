package com.fullsoft.sms.dto;

public class SmsRequest {

    private String telefono;
    private String mensaje;

    public SmsRequest() {
        
    }

    public SmsRequest(String telefono, String mensaje) {
        this.telefono = telefono;
        this.mensaje = mensaje;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}