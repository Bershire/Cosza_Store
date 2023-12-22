package com.example.coza_store.exception;

public class CategoryAlreadyExistException extends RuntimeException{
    private String message;

    public CategoryAlreadyExistException(String message) {
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
