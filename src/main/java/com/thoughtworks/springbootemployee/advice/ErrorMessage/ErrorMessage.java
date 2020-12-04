package com.thoughtworks.springbootemployee.advice.ErrorMessage;

public class ErrorMessage {
    private final String status;
    private final String message;

    public ErrorMessage(String message, String status) {
        this.message = message;
        this.status = status;
    }

}
