package com.example.coza_store.exception;

public class TagAlreadyExistException extends RuntimeException{
    private String message;

    public TagAlreadyExistException(String message) {
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
