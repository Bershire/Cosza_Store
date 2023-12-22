package com.example.coza_store.service;

import com.example.coza_store.entity.UserEntity;
import com.example.coza_store.exception.*;
import com.example.coza_store.payload.request.SignupRequest;
import com.example.coza_store.payload.response.UserResponse;
import com.example.coza_store.repository.UserRepository;
import com.example.coza_store.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAllUser() {
        List<UserResponse> responseList = new ArrayList<>();
        List<UserEntity> list = userRepository.findAll();
        for (UserEntity data : list) {
            UserResponse userReponse = new UserResponse();
            userReponse.setId(data.getId());
            userReponse.setEmail(data.getEmail());
            userReponse.setUsername(data.getUsername());
            userReponse.setPassword(data.getPassword());
            responseList.add(userReponse);
        }
        return responseList;
    }

    @Override
    public boolean addUser(SignupRequest request) {
        boolean isSuccess = false;
        try{
            UserEntity user = new UserEntity();
            user.setUsername(request.getUsername());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            userRepository.save(user);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi thêm User " + e.getMessage());
        }
        return isSuccess;
    }

    @Override
    public void editUser(Integer id, String username, String password, String email) {
        if(!email.trim().isEmpty()) {
            UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                    new UserNotFoundException("User not found with id: " + id));
            if(!userEntity.getEmail().equals(email)) {
                UserEntity existingUser = userRepository.findByEmail(email);
                if (existingUser != null) {
                    throw new TagAlreadyExistException("Duplicating email: " + email);
                }
                userEntity.setUsername(username);
                userEntity.setPassword(passwordEncoder.encode(password));
                userEntity.setEmail(email);
                userRepository.save(userEntity);
            }
        }
    }

    @Override
    public void deleteUser(Integer id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with id: " + id)
        );
        userRepository.delete(userEntity);
    }
}
