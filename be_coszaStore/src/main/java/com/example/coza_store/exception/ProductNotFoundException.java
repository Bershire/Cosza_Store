package com.example.coza_store.exception;

public class ProductNotFoundException extends RuntimeException{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ProductNotFoundException(String message) {
        this.message = message;
    }
}
