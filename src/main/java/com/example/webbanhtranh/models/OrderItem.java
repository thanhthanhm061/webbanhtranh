package com.example.webbanhtranh.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // Liên kết với đơn hàng
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artwork_id") // Liên kết với sản phẩm (artwork)
    private Artwork artwork;

    private int quantity;           // Số lượng sản phẩm trong đơn hàng
    private BigDecimal price;       // Giá của sản phẩm khi đặt hàng
    private BigDecimal totalPrice;  // Tổng tiền của sản phẩm (price * quantity)

    // Constructor mặc định
    public OrderItem() {
    }

    // Constructor với các tham số
    public OrderItem(Order order, Artwork artwork, int quantity, BigDecimal price) {
        this.order = order;
        this.artwork = artwork;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = price.multiply(BigDecimal.valueOf(quantity)); // Tính tổng tiền
    }

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = this.price.multiply(BigDecimal.valueOf(quantity)); // Cập nhật tổng tiền khi số lượng thay đổi
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
        this.totalPrice = price.multiply(BigDecimal.valueOf(quantity)); // Cập nhật tổng tiền khi giá thay đổi
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
