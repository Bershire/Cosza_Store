package com.example.coza_store.repository;

import com.example.coza_store.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    Optional<ColorEntity> findByName(String name);
}
