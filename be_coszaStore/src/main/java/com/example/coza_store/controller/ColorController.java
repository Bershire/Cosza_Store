package com.example.coza_store.controller;

import com.example.coza_store.payload.response.BaseResponse;
import com.example.coza_store.service.imp.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/color")
public class ColorController {

    @Autowired
    ColorServiceImp colorServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllColor() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(colorServiceImp.getAllColor());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addColor(@RequestParam String name) {
        colorServiceImp.add(name);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Add color successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editColor(@PathVariable Integer id,
                                       @RequestParam String name) {
        colorServiceImp.edit(id, name);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Edit color successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable Integer id) {
        colorServiceImp.delete(id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Delete color successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
