package com.example.coza_store.service;

import com.example.coza_store.entity.CategoryEntity;
import com.example.coza_store.entity.CountryEntity;
import com.example.coza_store.entity.ProductEntity;
import com.example.coza_store.entity.SizeEntity;
import com.example.coza_store.exception.*;
import com.example.coza_store.payload.response.CategoryResponse;
import com.example.coza_store.payload.response.ProductResponse;
import com.example.coza_store.payload.response.SizeResponse;
import com.example.coza_store.repository.CategoryRepository;
import com.example.coza_store.repository.ProductRepository;
import com.example.coza_store.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProduct() {
        List<ProductResponse> responseList = new ArrayList<>();
        List<ProductEntity> list = productRepository.findAll();
        for (ProductEntity data : list) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(data.getId());
            productResponse.setImage(data.getImage());
            productResponse.setName(data.getName());
            productResponse.setPrice(data.getPrice());
            productResponse.setDescription(data.getDescription());
            productResponse.setQuantity(data.getQuantity());
            productResponse.setCategoryId(data.getCategory().getId());
            responseList.add(productResponse);
        }
        return responseList;
    }

    @Override
    public List<ProductResponse> getProductByCategory(int id) {
        List<ProductEntity> list = productRepository.findByCategoryId(id);
        List<ProductResponse> responseList = new ArrayList<>();
        for (ProductEntity data : list) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(data.getId());
            productResponse.setName(data.getName());
            productResponse.setImage(data.getImage());
            productResponse.setPrice(data.getPrice());
            responseList.add(productResponse);
        }
        return responseList;
    }

    @Override
    public ProductResponse getDetailProduct(int id) {
        ProductEntity detailProduct = productRepository.findById(id);
        ProductResponse response = new ProductResponse();
        response.setId(detailProduct.getId());
        response.setName(detailProduct.getName());
        response.setDescription(detailProduct.getDescription());
        response.setPrice(detailProduct.getPrice());
        response.setImageDetail(detailProduct.getImageDetail());
        return response;
    }

    @Override
    public void addProductToOrder(ProductResponse productResponse) {

    }

    @Override
    public void add(String image, String name, double price, String description, int quantity, String imageDetail, CategoryEntity categoryId) {
        if (!(name == null || name.trim().isEmpty())) {
            productRepository.findByName(name).ifPresent(existingSize -> {
                throw new ProductAlreadyExistException(name + " already exist!");
            });
            ProductEntity productEntity = new ProductEntity();
            productEntity.setName(name);
            productEntity.setPrice(price);
            productEntity.setImage(image);
            productEntity.setDescription(description);
            productEntity.setQuantity(quantity);
            productEntity.setImageDetail(imageDetail);
            productEntity.setCategory(categoryId);
            productRepository.save(productEntity);
        } else {
            throw new CustomIllegalArgumentException("Illegal name: " + name);
        }
    }

    @Override
    public void edit(Integer id, String image, String name, double price, String description, int quantity, String imageDetail, CategoryEntity categoryId) {
        if(!name.trim().isEmpty()) {
            ProductEntity productEntity = productRepository.findById(id).orElseThrow(() ->
                    new ProductNotFoundException("Product not found with id: " + id));
            if(!productEntity.getName().equals(name)) {
                productRepository.findByName(name).ifPresent(existingSize -> {
                    throw new ProductAlreadyExistException("Duplicating product name: " + name);
                });
                productEntity.setName(name);
                productEntity.setPrice(price);
                productEntity.setImage(image);
                productEntity.setDescription(description);
                productEntity.setQuantity(quantity);
                productEntity.setImageDetail(imageDetail);
                productEntity.setCategory(categoryId);
                productRepository.save(productEntity);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("Product not found with id: " + id)
        );
        productRepository.delete(productEntity);
    }
}
