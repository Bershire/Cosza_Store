package com.example.coza_store.service;

import com.example.coza_store.entity.BlogEntity;
import com.example.coza_store.payload.response.BlogResponse;
import com.example.coza_store.repository.BlogRepository;
import com.example.coza_store.service.imp.BlogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService implements BlogServiceImp {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<BlogResponse> getAllBlog() {
        List<BlogResponse> responseList = new ArrayList<>();
        List<BlogEntity> list = blogRepository.findAll();
        for (BlogEntity data : list) {
            BlogResponse response = new BlogResponse();
            response.setId(data.getId());
            response.setImage(data.getImage());
            response.setTitle(data.getTitle());
            response.setDescription(data.getDescription());
            response.setUserId(data.getId());
            response.setContent(data.getContent());
            response.setCreateDate(data.getCreateDate());
            responseList.add(response);
        }
        return responseList;
    }
}
