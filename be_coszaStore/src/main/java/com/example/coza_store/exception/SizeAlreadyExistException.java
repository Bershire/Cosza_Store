package com.example.coza_store.exception;

public class SizeAlreadyExistException extends RuntimeException{
    private String name;

    public SizeAlreadyExistException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
