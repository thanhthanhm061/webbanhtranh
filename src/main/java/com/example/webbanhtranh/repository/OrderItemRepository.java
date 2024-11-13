package com.example.webbanhtranh.repository;

import com.example.webbanhtranh.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
