package com.example.coza_store.service;

import com.example.coza_store.entity.CategoryEntity;
import com.example.coza_store.entity.SizeEntity;
import com.example.coza_store.exception.ColorAlreadyExistException;
import com.example.coza_store.exception.CustomIllegalArgumentException;
import com.example.coza_store.exception.SizeAlreadyExistException;
import com.example.coza_store.exception.SizeNotFoundException;
import com.example.coza_store.payload.response.CategoryResponse;
import com.example.coza_store.payload.response.SizeResponse;
import com.example.coza_store.repository.SizeRepository;
import com.example.coza_store.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeService implements SizeServiceImp {

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<SizeResponse> getAllSize() {
        List<SizeResponse> responseList = new ArrayList<>();
        List<SizeEntity> list = sizeRepository.findAll();
        for (SizeEntity data : list) {
            SizeResponse sizeResponse = new SizeResponse();
            sizeResponse.setId(data.getId());
            sizeResponse.setName(data.getName());
            responseList.add(sizeResponse);
        }
        return responseList;
    }

    @Override
    public void add(String name) {
        if (!(name == null || name.trim().isEmpty())) {
            sizeRepository.findByName(name).ifPresent(existingSize -> {
                throw new SizeAlreadyExistException(name + " already exist!");
            });
            SizeEntity sizeEntity = new SizeEntity();
            sizeEntity.setName(name);
            sizeRepository.save(sizeEntity);
        } else {
            throw new CustomIllegalArgumentException("Illegal name: " + name);
        }
    }

    @Override
    public void edit(Integer id, String name) {
        if(!name.trim().isEmpty()) {
            SizeEntity sizeEntity = sizeRepository.findById(id).orElseThrow(() ->
                    new SizeNotFoundException("Size not found with id: " + id));
            if(!sizeEntity.getName().equals(name)) {
                sizeRepository.findByName(name).ifPresent(existingSize -> {
                    throw new SizeAlreadyExistException("Duplicating size name: " + name);
                });
                sizeEntity.setName(name);
                sizeRepository.save(sizeEntity);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        SizeEntity sizeEntity = sizeRepository.findById(id).orElseThrow(() ->
                new SizeNotFoundException("Size not found with id: " + id)
        );
        sizeRepository.delete(sizeEntity);
    }
}
