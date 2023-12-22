package com.example.coza_store.service.imp;

import com.example.coza_store.entity.CategoryEntity;
import com.example.coza_store.payload.response.ProductResponse;

import java.util.List;


public interface ProductServiceImp {
    List<ProductResponse> getAllProduct();
    List<ProductResponse> getProductByCategory(int id);
    ProductResponse getDetailProduct(int id);
    void addProductToOrder(ProductResponse productResponse);

    void add (String image, String name, double price, String description, int quantity, String imageDetail, CategoryEntity categoryId);

    void edit (Integer id, String image, String name, double price, String description, int quantity, String imageDetail, CategoryEntity categoryId);

    void delete (Integer id);
}
