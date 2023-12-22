package com.example.coza_store.service;

import com.example.coza_store.entity.OrderDetailEntity;
import com.example.coza_store.payload.response.OrderDetailResponse;
import com.example.coza_store.repository.OrderDetailRepository;
import com.example.coza_store.service.imp.OrderDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailService implements OrderDetailServiceImp {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailResponse> getAllProductOrder(int id) {
        List<OrderDetailResponse> responseList = new ArrayList<>();
        List<OrderDetailEntity> list = orderDetailRepository.getByOderId(id);
        for (OrderDetailEntity data : list) {
            System.out.println(data.getProduct().getImage());
            System.out.println(data.getProduct().getName());
            System.out.println(data.getProduct().getPrice());
            System.out.println(data.getQuantity());
            OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
            orderDetailResponse.setImages(data.getProduct().getImage());
            orderDetailResponse.setProductName(data.getProduct().getName());
            orderDetailResponse.setPrice(data.getProduct().getPrice());
            orderDetailResponse.setQuantity(data.getQuantity());
            responseList.add(orderDetailResponse);
        }
        return responseList;
    }
}
