package com.example.coza_store.service;

import com.example.coza_store.entity.CategoryEntity;
import com.example.coza_store.entity.ColorEntity;
import com.example.coza_store.entity.CountryEntity;
import com.example.coza_store.exception.*;
import com.example.coza_store.payload.response.CategoryResponse;
import com.example.coza_store.repository.CategoryRepository;
import com.example.coza_store.service.imp.CategoryServiceImp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
//    @Cacheable("listCategory")
    public List<CategoryResponse> getAllCategory() {
        System.out.println("Có giá trị trên redis");
        List<CategoryResponse> responseList = new ArrayList<>();
        if (redisTemplate.hasKey("listCategory")) {
            //  Nếu như có thì lấy giá trị lưu trữ trên redis
            String data = redisTemplate.opsForValue().get("listCategory").toString();
            Type listType = new TypeToken<ArrayList<CategoryResponse>>(){}.getType();
            responseList = new Gson().fromJson(data, listType);
        } else {
            System.out.println("Không có giá trị trên redis");
            List<CategoryEntity> list = categoryRepository.findAll();
            for (CategoryEntity data : list) {
                CategoryResponse categoryResponse = new CategoryResponse();
                categoryResponse.setId(data.getId());
                categoryResponse.setName(data.getName());
                responseList.add(categoryResponse);
            }
        }
        Gson gson = new Gson();
        String data = gson.toJson(responseList);
        redisTemplate.opsForValue().set("listCategory", data);
        return responseList;
    }

    @Override
    public void add(String name) {
        if (!(name == null || name.trim().isEmpty())) {
            categoryRepository.findByName(name).ifPresent(existingSize -> {
                throw new CategoryAlreadyExistException(name + " already exist!");
            });
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(name);
            categoryRepository.save(categoryEntity);
        } else {
            throw new CustomIllegalArgumentException("Illegal name: " + name);
        }
    }

    @Override
    public void edit(Integer id, String name) {
        if(!name.trim().isEmpty()) {
            CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() ->
                    new CategoryNotFoundException("Category not found with id: " + id));
            if(!categoryEntity.getName().equals(name)) {
                categoryRepository.findByName(name).ifPresent(existingSize -> {
                    throw new CategoryAlreadyExistException("Duplicating category name: " + name);
                });
                categoryEntity.setName(name);
                categoryRepository.save(categoryEntity);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(() ->
                new CategoryNotFoundException("Size not found with id: " + id)
        );
        categoryRepository.delete(categoryEntity);
    }
}
