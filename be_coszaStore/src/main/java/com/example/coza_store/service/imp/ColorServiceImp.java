package com.example.coza_store.service.imp;

import com.example.coza_store.entity.ColorEntity;
import com.example.coza_store.payload.response.ColorResponse;

import java.util.List;

public interface ColorServiceImp {
    List<ColorResponse> getAllColor();

    void add(String name);

    void edit (Integer id, String name);

    void delete (Integer id);
}
