package com.example.coza_store.controller;

import com.example.coza_store.payload.response.BaseResponse;
import com.example.coza_store.service.imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogServiceImp blogServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllBlog() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(blogServiceImp.getAllBlog());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
