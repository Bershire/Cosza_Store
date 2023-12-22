package com.example.coza_store.controller;

import com.example.coza_store.payload.response.BaseResponse;
import com.example.coza_store.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping("/clear-cache")
    @CacheEvict(value = "listCategory", allEntries = true)
    public ResponseEntity<?> clearCache() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(categoryServiceImp.getAllCategory());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestParam String name) {
        categoryServiceImp.add(name);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Add category successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Integer id,
                                          @RequestParam String name) {
        categoryServiceImp.edit(id, name);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Edit successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        categoryServiceImp.delete(id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Successfully delete!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
