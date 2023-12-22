package com.example.coza_store.exception;

public class CountryAlreadyExistException extends RuntimeException{
    private String name;

    public CountryAlreadyExistException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
