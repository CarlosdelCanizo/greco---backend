package com.greco.exception;


public class APIException extends RuntimeException {
    private String message;

    APIException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
