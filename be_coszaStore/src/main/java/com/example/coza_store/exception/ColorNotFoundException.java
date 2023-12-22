package com.example.coza_store.exception;

public class ColorNotFoundException extends RuntimeException{
    private String name;

    public ColorNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
