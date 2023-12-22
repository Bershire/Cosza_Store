package com.example.coza_store.service.imp;

import com.example.coza_store.payload.response.CategoryResponse;

import java.util.List;

public interface CategoryServiceImp {
    List<CategoryResponse> getAllCategory();

    void add(String name);

    void edit (Integer id, String name);

    void delete (Integer id);

}
