package com.example.coza_store.controller;

import com.example.coza_store.payload.response.BaseResponse;
import com.example.coza_store.payload.response.OrderDetailResponse;
import com.example.coza_store.service.imp.OrderDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderDetailServiceImp orderDetailServiceImp;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllProductOrder(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(orderDetailServiceImp.getAllProductOrder(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
