package com.example.coza_store.controller;

import com.example.coza_store.entity.CategoryEntity;
import com.example.coza_store.payload.response.BaseResponse;
import com.example.coza_store.service.imp.ProductServiceImp;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    private Gson gson = new Gson();

    @GetMapping("")
    public ResponseEntity<?> getAllProduct() {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceImp.getAllProduct());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductByCategory(@PathVariable int id) {
        logger.info("Tham so " + id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceImp.getProductByCategory(id));
        logger.info(gson.toJson(response));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailProduct(@PathVariable int id) {
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setData(productServiceImp.getDetailProduct(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestParam String name,
                                        @RequestParam double price,
                                        @RequestParam String image,
                                        @RequestParam String description,
                                        @RequestParam int quantity,
                                        @RequestParam String imageDetail,
                                        @RequestParam CategoryEntity categoryId) {
        productServiceImp.add(image, name, price, description, quantity, imageDetail, categoryId);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Add product successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProduct(@PathVariable int id,
                                         @RequestParam String name,
                                         @RequestParam double price,
                                         @RequestParam String image,
                                         @RequestParam String description,
                                         @RequestParam int quantity,
                                         @RequestParam String imageDetail,
                                         @RequestParam CategoryEntity categoryId) {
        productServiceImp.edit(id, image, name, price, description, quantity, imageDetail, categoryId);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Edit product successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {
        productServiceImp.delete(id);
        BaseResponse response = new BaseResponse();
        response.setStatusCode(200);
        response.setMessage("Delete product successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
