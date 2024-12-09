package com.example.webbanhtranh.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)   // Liên kết với Artwork
    @JoinColumn(name = "artwork_id")  // Chỉ định tên cột khóa ngoại
    private Artwork artwork;

    private int quantity;   // Số lượng sản phẩm

    private BigDecimal totalPrice;  // Tổng giá trị của CartItem (tính bằng giá * số lượng)

    public CartItem() {}

    public CartItem(Artwork artwork, int quantity) {
        this.artwork = artwork;
        this.quantity = quantity;
        this.totalPrice = artwork.getPrice().multiply(BigDecimal.valueOf(quantity));  // Tính tổng giá trị
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
