package com.example.coza_store.service;

import com.example.coza_store.entity.CountryEntity;
import com.example.coza_store.entity.SizeEntity;
import com.example.coza_store.exception.*;
import com.example.coza_store.payload.response.CountryResponse;
import com.example.coza_store.repository.CountryRepository;
import com.example.coza_store.service.imp.CountryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService implements CountryServiceImp {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<CountryResponse> getAllCountry() {
        List<CountryResponse> responsesList = new ArrayList<>();
        List<CountryEntity> list = countryRepository.findAll();
        for (CountryEntity data : list) {
            CountryResponse countryResponse = new CountryResponse();
            countryResponse.setId(data.getId());
            countryResponse.setName(data.getName());
            countryResponse.setPriceShip(data.getPriceShip());
            responsesList.add(countryResponse);
        }
        return responsesList;
    }

    @Override
    public void add(String name, double priceShip) {
        if (!(name == null || name.trim().isEmpty())) {
            countryRepository.findByName(name).ifPresent(existingSize -> {
                throw new CountryAlreadyExistException(name + " already exist!");
            });
            CountryEntity countryEntity = new CountryEntity();
            countryEntity.setName(name);
            countryEntity.setPriceShip(priceShip);
            countryRepository.save(countryEntity);
        } else {
            throw new CustomIllegalArgumentException("Illegal name: " + name);
        }
    }

    @Override
    public void edit(Integer id, String name, double priceShip) {
        if(!name.trim().isEmpty()) {
            CountryEntity countryEntity = countryRepository.findById(id).orElseThrow(() ->
                    new CountryNotFoundException("Country not found with id: " + id));
            if(!countryEntity.getName().equals(name)) {
                countryRepository.findByName(name).ifPresent(existingSize -> {
                    throw new CountryAlreadyExistException("Duplicating country name: " + name);
                });
                countryEntity.setName(name);
                countryEntity.setPriceShip(priceShip);
                countryRepository.save(countryEntity);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        CountryEntity countryEntity = countryRepository.findById(id).orElseThrow(() ->
                new CountryNotFoundException("Country not found with id: " + id)
        );
        countryRepository.delete(countryEntity);
    }

    @Override
    public void find(String keyword) {
        List<CountryEntity> list = countryRepository.findAll();
        List<CountryEntity> searchResult = list.stream()
                .filter(product -> product.getName().contains(keyword))
                .collect(Collectors.toList());

    }
}
