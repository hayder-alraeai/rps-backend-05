package com.hayder.rpsbackend0310;

public class ValidationResponse {
    private String message;

    public ValidationResponse(String message) {
        this.message = message;
    }

    public ValidationResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
