package com.example.coza_store.controller;

import com.example.coza_store.payload.response.BaseResponse;
import com.example.coza_store.service.imp.TagServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    TagServiceImp tagServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllTag() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(tagServiceImp.getAllTag());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTag(@RequestParam String name) {
        tagServiceImp.add(name);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Add tag successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editTag(@PathVariable Integer id,
                                     @RequestParam String name) {
        tagServiceImp.edit(id, name);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Edit tag successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable Integer id) {
        tagServiceImp.delete(id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Delete tag successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
