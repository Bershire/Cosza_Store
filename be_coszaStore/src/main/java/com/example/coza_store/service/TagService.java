package com.example.coza_store.service;

import com.example.coza_store.entity.SizeEntity;
import com.example.coza_store.entity.TagEntity;
import com.example.coza_store.exception.*;
import com.example.coza_store.payload.response.TagResponse;
import com.example.coza_store.repository.TagRepository;
import com.example.coza_store.service.imp.TagServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService implements TagServiceImp {
    @Autowired
    TagRepository tagRepository;

    @Override
    public List<TagResponse> getAllTag() {
        List<TagResponse> responseList = new ArrayList<>();
        List<TagEntity> list = tagRepository.findAll();
        for (TagEntity data : list) {
            TagResponse tagResponse = new TagResponse();
            tagResponse.setId(data.getId());
            tagResponse.setName(data.getName());
            responseList.add(tagResponse);
        }
        return responseList;
    }

    @Override
    public void add(String name) {
        if (!(name == null || name.trim().isEmpty())) {
            tagRepository.findByName(name).ifPresent(existingSize -> {
                throw new TagAlreadyExistException(name + " already exist!");
            });
            TagEntity tagEntity = new TagEntity();
            tagEntity.setName(name);
            tagRepository.save(tagEntity);
        } else {
            throw new CustomIllegalArgumentException("Illegal name: " + name);
        }
    }

    @Override
    public void edit(Integer id, String name) {
        if(!name.trim().isEmpty()) {
            TagEntity tagEntity = tagRepository.findById(id).orElseThrow(() ->
                    new TagNotFoundException("Tag not found with id: " + id));
            if(!tagEntity.getName().equals(name)) {
                tagRepository.findByName(name).ifPresent(existingTag -> {
                    throw new TagAlreadyExistException("Duplicating size name: " + name);
                });
                tagEntity.setName(name);
                tagRepository.save(tagEntity);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        TagEntity tagEntity = tagRepository.findById(id).orElseThrow(() ->
                new SizeNotFoundException("Size not found with id: " + id)
        );
        tagRepository.delete(tagEntity);
    }
}
