package com.example.coza_store.service.imp;

import com.example.coza_store.payload.response.CountryResponse;

import java.util.List;

public interface CountryServiceImp {
    List<CountryResponse> getAllCountry();

    void add (String name, double priceShip);

    void edit (Integer id, String name, double priceShip);

    void delete (Integer id);

    void find(String keyword);
}
