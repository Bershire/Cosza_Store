package com.example.coza_store.service.imp;

import com.example.coza_store.payload.request.SignupRequest;
import com.example.coza_store.payload.response.UserResponse;

import java.util.List;

public interface UserServiceImp {
    boolean addUser(SignupRequest request);

    List<UserResponse> getAllUser();

    void editUser(Integer id, String username, String password, String email);

    void deleteUser(Integer id);
}
