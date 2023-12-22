package com.example.coza_store.repository;

import com.example.coza_store.entity.ProductEntity;
import com.example.coza_store.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCategoryId(int idCategory);
    ProductEntity findById(int idProduct);

    Optional<ProductEntity> findByName(String name);
}
