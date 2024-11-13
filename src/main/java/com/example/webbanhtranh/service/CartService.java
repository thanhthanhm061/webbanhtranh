package com.example.webbanhtranh.service;

import com.example.webbanhtranh.models.Artwork;
import com.example.webbanhtranh.models.CartItem;
import com.example.webbanhtranh.repository.CartItemRepository;
import com.example.webbanhtranh.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ArtworkRepository artworkRepository;

    // Lấy danh sách các sản phẩm trong giỏ hàng
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    // Thêm sản phẩm vào giỏ hàng
    public void addCartItem(Long artworkId, int quantity) {
        // Kiểm tra xem sản phẩm có tồn tại không
        Optional<Artwork> artworkOptional = artworkRepository.findById(artworkId);
        if (artworkOptional.isPresent()) {
            Artwork artwork = artworkOptional.get();

            // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            Optional<CartItem> existingCartItem = cartItemRepository.findByArtworkId(artworkId);
            if (existingCartItem.isPresent()) {
                // Nếu sản phẩm đã có, cập nhật số lượng
                CartItem item = existingCartItem.get();
                item.setQuantity(item.getQuantity() + quantity);
                cartItemRepository.save(item);
            } else {
                // Nếu chưa có, thêm sản phẩm mới vào giỏ
                CartItem cartItem = new CartItem();
                cartItem.setArtwork(artwork);
                cartItem.setQuantity(quantity);
                cartItem.setTotalPrice(artwork.getPrice().multiply(BigDecimal.valueOf(quantity))); // Tính giá trị sản phẩm
                cartItemRepository.save(cartItem);
            }
        } else {
            // Nếu không tìm thấy artwork, có thể báo lỗi hoặc thực hiện xử lý khác
            throw new RuntimeException("Sản phẩm không tồn tại!");
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    public void updateCartItemQuantity(Long cartItemId, int quantity) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(quantity);
            cartItem.setTotalPrice(cartItem.getArtwork().getPrice().multiply(BigDecimal.valueOf(quantity))); // Cập nhật lại giá trị
            cartItemRepository.save(cartItem);
        }
    }

    // Tính tổng giá trị giỏ hàng
    public BigDecimal getTotalCartValue() {
        List<CartItem> cartItems = cartItemRepository.findAll();
        BigDecimal totalValue = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            totalValue = totalValue.add(cartItem.getTotalPrice());
        }
        return totalValue;
    }
}
