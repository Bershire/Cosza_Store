package com.example.coza_store.service;

import com.example.coza_store.entity.ColorEntity;
import com.example.coza_store.entity.SizeEntity;
import com.example.coza_store.exception.*;
import com.example.coza_store.payload.response.ColorResponse;
import com.example.coza_store.repository.ColorRepository;
import com.example.coza_store.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements ColorServiceImp {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<ColorResponse> getAllColor() {
        List<ColorResponse> responseList = new ArrayList<>();
        List<ColorEntity> list = colorRepository.findAll();
        for (ColorEntity data : list) {
            ColorResponse colorResponse = new ColorResponse();
            colorResponse.setId(data.getId());
            colorResponse.setName(data.getName());
            responseList.add(colorResponse);
        }
        return responseList;
    }

    @Override
    public void add(String name) {
        if (!(name == null || name.trim().isEmpty())) {
            colorRepository.findByName(name).ifPresent(existingColor -> {
                throw new ColorAlreadyExistException(name + " already exist!");
            });
            ColorEntity colorEntity = new ColorEntity();
            colorEntity.setName(name);
            colorRepository.save(colorEntity);
        } else {
            throw new CustomIllegalArgumentException("Illegal name: " + name);
        }
    }

    @Override
    public void edit(Integer id, String name) {
        if(!name.trim().isEmpty()) {
            ColorEntity colorEntity = colorRepository.findById(id).orElseThrow(() ->
                    new SizeNotFoundException("Size not found with id: " + id));
            if(!colorEntity.getName().equals(name)) {
                colorRepository.findByName(name).ifPresent(existingSize -> {
                    throw new SizeAlreadyExistException("Duplicating size name: " + name);
                });
                colorEntity.setName(name);
                colorRepository.save(colorEntity);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        ColorEntity colorEntity = colorRepository.findById(id).orElseThrow(() ->
                new ColorNotFoundException("Size not found with id: " + id)
        );
        colorRepository.delete(colorEntity);
    }


}
