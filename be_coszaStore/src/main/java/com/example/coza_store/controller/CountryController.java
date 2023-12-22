package com.example.coza_store.controller;

import com.example.coza_store.payload.response.BaseResponse;
import com.example.coza_store.service.imp.CountryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    CountryServiceImp countryServiceImp;
    @GetMapping("")
    public ResponseEntity<?> getAllCountry() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(countryServiceImp.getAllCountry());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCountry(@RequestParam String name, @RequestParam double priceShip) {
        countryServiceImp.add(name, priceShip);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Add country successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCountry(@PathVariable Integer id,
                                         @RequestParam String name,
                                         @RequestParam double priceShip) {
        countryServiceImp.edit(id, name, priceShip);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Edit country successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer id) {
        countryServiceImp.delete(id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Delete country successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
