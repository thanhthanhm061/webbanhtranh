package com.example.webbanhtranh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.webbanhtranh.models.Artwork;
import com.example.webbanhtranh.service.ArtworkService;

import java.util.List;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkApiController {

    @Autowired
    private ArtworkService artworkService;

    // Lấy danh sách tất cả artworks
    @GetMapping
    public List<Artwork> getAllArtworks() {
        return artworkService.getAllArtworks();
    }

    // Lấy chi tiết artwork theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Artwork> getArtworkById(@PathVariable Long id) {
        Artwork artwork = artworkService.getArtworkById(id);
        if (artwork != null) {
            return ResponseEntity.ok(artwork);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Thêm artwork mới
    @PostMapping
    public ResponseEntity<Artwork> createArtwork(@RequestBody Artwork artwork) {
        Artwork newArtwork = artworkService.saveArtwork(artwork);
        return ResponseEntity.status(HttpStatus.CREATED).body(newArtwork);
    }

    // Cập nhật artwork
    @PutMapping("/{id}")
    public ResponseEntity<Artwork> updateArtwork(@PathVariable Long id, @RequestBody Artwork updatedArtwork) {
        Artwork existingArtwork = artworkService.getArtworkById(id);
        if (existingArtwork != null) {
            // Cập nhật thông tin
            existingArtwork.setTitle(updatedArtwork.getTitle());
            existingArtwork.setPrice(updatedArtwork.getPrice());
            existingArtwork.setPrice(updatedArtwork.getPrice());
            // Lưu lại
            Artwork savedArtwork = artworkService.saveArtwork(existingArtwork);
            return ResponseEntity.ok(savedArtwork);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable Long id) {
        Artwork existingArtwork = artworkService.getArtworkById(id);
        if (existingArtwork != null) {
            artworkService.deleteArtwork(id);
            return ResponseEntity.noContent().build(); // HTTP 204
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
