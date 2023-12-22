package com.example.coza_store.service.imp;

import com.example.coza_store.payload.response.TagResponse;

import java.util.List;

public interface TagServiceImp {
    List<TagResponse> getAllTag();

    void add(String name);

    void edit(Integer id, String name);

    void delete(Integer id);
}
