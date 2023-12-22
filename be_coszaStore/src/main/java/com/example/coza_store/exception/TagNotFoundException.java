package com.example.coza_store.exception;

public class TagNotFoundException extends RuntimeException{
    private String message;

    public TagNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
