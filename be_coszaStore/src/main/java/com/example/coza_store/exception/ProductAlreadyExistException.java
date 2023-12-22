package com.example.coza_store.exception;

public class ProductAlreadyExistException extends RuntimeException{
    private String message;

    public ProductAlreadyExistException(String message) {
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
