package com.example.webbanhtranh.controller;

import com.example.webbanhtranh.service.CartService;
import com.example.webbanhtranh.models.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private CartService cartService;

    // Hiển thị trang thanh toán
    @GetMapping("/checkout")
    public String showCheckoutPage(Model model) {
        List<CartItem> cartItems = cartService.getCartItems();
        BigDecimal totalAmount = cartService.getTotalCartValue();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "checkout";
    }

    // Xử lý thông tin thanh toán khi người dùng gửi form
    @PostMapping("/checkout/submit")
    public String submitCheckout(
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            Model model) {

        // Xử lý thanh toán, bạn có thể thêm logic như gửi email, lưu thông tin đơn hàng vào cơ sở dữ liệu, v.v.
        // Ví dụ, bạn chỉ cần thông báo đơn hàng đã được xử lý thành công.

        // Lấy thông tin giỏ hàng
        List<CartItem> cartItems = cartService.getCartItems();
        BigDecimal totalAmount = cartService.getTotalCartValue();

        // Thêm thông tin vào model để hiển thị thông báo hoặc chuyển hướng
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("customerName", name);
        model.addAttribute("customerAddress", address);

        // Chuyển hướng đến trang thành công hoặc thông báo đã thanh toán
        return "orderConfirmation"; // Tạo một trang orderConfirmation.html để hiển thị kết quả thanh toán
    }
}
