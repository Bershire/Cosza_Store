package com.example.coza_store.repository;

import com.example.coza_store.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer> {
    @Query("from order_detail as od where od.order.id = ?1")
    List<OrderDetailEntity> getByOderId(int id);
}
