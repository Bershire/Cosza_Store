package com.example.coza_store.repository;

import com.example.coza_store.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {
}
