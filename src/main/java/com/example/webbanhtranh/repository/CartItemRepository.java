package com.example.webbanhtranh.repository;

import com.example.webbanhtranh.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByArtworkId(Long artworkId);
}
