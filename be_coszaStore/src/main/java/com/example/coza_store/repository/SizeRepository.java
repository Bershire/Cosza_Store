package com.example.coza_store.repository;

import com.example.coza_store.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {
    Optional<SizeEntity> findByName(String name);
}
