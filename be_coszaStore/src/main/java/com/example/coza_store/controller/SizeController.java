package com.example.coza_store.controller;

import com.example.coza_store.payload.response.BaseResponse;
import com.example.coza_store.service.imp.CategoryServiceImp;
import com.example.coza_store.service.imp.SizeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/size")
public class SizeController {
    @Autowired
    SizeServiceImp sizeServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllSize() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(sizeServiceImp.getAllSize());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addSize(@RequestParam String name) {
        sizeServiceImp.add(name);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Add size successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editSize(@PathVariable Integer id,
                                      @RequestParam String name) {
        sizeServiceImp.edit(id, name);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Edit size successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Integer id) {
        sizeServiceImp.delete(id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Delete size successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
