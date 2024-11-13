package com.example.webbanhtranh.controller;

import com.example.webbanhtranh.models.CartItem;
import com.example.webbanhtranh.service.CartService;
import com.example.webbanhtranh.models.Artwork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    // Hiển thị giỏ hàng
    @GetMapping("/cart")
    public String viewCart(Model model) {
        // Lấy danh sách các sản phẩm trong giỏ
        model.addAttribute("cartItems", cartService.getCartItems());
        // Tính tổng tiền giỏ hàng
        model.addAttribute("totalPrice", cartService.getTotalCartValue());
        return "cart"; // Đây là trang cart.html trong thư mục templates
    }

    // Thêm sản phẩm vào giỏ
    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("artworkId") Long artworkId, @RequestParam("quantity") int quantity) {
        cartService.addCartItem(artworkId, quantity);
        return "redirect:/cart";  // Quay lại trang giỏ hàng
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PostMapping("/cart/update")
    public String updateCartItem(@RequestParam("cartItemId") Long cartItemId, @RequestParam("quantity") int quantity) {
        cartService.updateCartItemQuantity(cartItemId, quantity);
        return "redirect:/cart";
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @PostMapping("/cart/remove")
    public String removeCartItem(@RequestParam("cartItemId") Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return "redirect:/cart";
    }
}
