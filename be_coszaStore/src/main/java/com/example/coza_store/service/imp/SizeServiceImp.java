package com.example.coza_store.service.imp;

import com.example.coza_store.payload.response.SizeResponse;

import java.util.List;

public interface SizeServiceImp {
    List<SizeResponse> getAllSize();
    void add(String name);
    void edit(Integer id, String name);
    void delete(Integer id);
}
