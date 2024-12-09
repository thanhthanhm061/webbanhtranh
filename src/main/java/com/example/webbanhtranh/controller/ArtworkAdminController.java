package com.example.webbanhtranh.controller;

import com.example.webbanhtranh.models.Artwork;
import com.example.webbanhtranh.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/artworks")
public class ArtworkAdminController {

    @Autowired
    private ArtworkService artworkService;

    // Hiển thị danh sách tác phẩm
    @GetMapping
    public String getAllArtworks(Model model) {
        List<Artwork> artworks = artworkService.getAllArtworks();
        model.addAttribute("artworks", artworks);
        return "admin_artworks"; // Trả về trang quản lý tác phẩm
    }

    // Hiển thị form thêm tác phẩm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("artwork", new Artwork());
        return "add_artwork"; // Form thêm tác phẩm mới
    }

    // Thêm tác phẩm mới
    @PostMapping("/add")
    public String addArtwork(@ModelAttribute Artwork artwork) {
        artworkService.saveArtwork(artwork);
        return "redirect:/admin/artworks"; // Quay lại trang danh sách
    }

    // Hiển thị form chỉnh sửa tác phẩm
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Artwork artwork = artworkService.getArtworkById(id);
        model.addAttribute("artwork", artwork);
        return "edit_artwork"; // Form chỉnh sửa tác phẩm
    }

    // Cập nhật tác phẩm
    @PostMapping("/edit/{id}")
    public String updateArtwork(@PathVariable Long id, @ModelAttribute Artwork artwork) {
        artwork.setId(id);
        artworkService.saveArtwork(artwork);
        return "redirect:/admin/artworks"; // Quay lại trang danh sách
    }

    // Xóa tác phẩm
    @PostMapping("/delete/{id}")
    public String deleteArtwork(@PathVariable Long id) {
        artworkService.deleteArtwork(id);
        return "redirect:/admin/artworks"; // Quay lại trang danh sách
    }
}
