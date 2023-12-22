package com.example.coza_store.service.imp;

import com.example.coza_store.payload.response.OrderDetailResponse;

import java.util.List;

public interface OrderDetailServiceImp {
    List<OrderDetailResponse> getAllProductOrder(int id);
}
