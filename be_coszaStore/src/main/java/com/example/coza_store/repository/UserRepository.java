package com.example.coza_store.repository;

import com.example.coza_store.entity.SizeEntity;
import com.example.coza_store.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
//    @Query("from users where email = ?1")
//    List<UserEntity> getUserByEmail(String email);
    UserEntity findByEmail(String email);
}
