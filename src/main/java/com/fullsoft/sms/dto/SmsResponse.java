package com.fullsoft.sms.dto;

public class SmsResponse {

    private String message;

    public SmsResponse() {

    }

    public SmsResponse(String message) {
        this.message = message;

    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
