package com.example.webbanhtranh.service;

import com.example.webbanhtranh.models.CartItem;
import com.example.webbanhtranh.models.Order;
import com.example.webbanhtranh.models.OrderItem;
import com.example.webbanhtranh.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Chuyển đổi CartItems thành OrderItems và lưu vào DB
    public void createOrderFromCart(Order order, List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);  // Liên kết với đơn hàng hiện tại
            orderItem.setArtwork(cartItem.getArtwork());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getArtwork().getPrice());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItemRepository.save(orderItem); // Lưu OrderItem vào cơ sở dữ liệu
        }
    }
}
