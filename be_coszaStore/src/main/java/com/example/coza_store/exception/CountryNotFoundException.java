package com.example.coza_store.exception;

public class CountryNotFoundException extends RuntimeException{
    private String name;

    public CountryNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
