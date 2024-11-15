package com.example.webbanhtranh.service;

import com.example.webbanhtranh.models.Artwork;
import com.example.webbanhtranh.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    // Lấy danh sách tất cả artworks
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    // Lấy chi tiết artwork theo ID
    public Artwork getArtworkById(Long id) {
        return artworkRepository.findById(id).orElse(null);
    }

    // Lưu hoặc thêm mới artwork
    public Artwork saveArtwork(Artwork artwork) {
        return artworkRepository.save(artwork);
    }

    // Cập nhật artwork
    public Artwork updateArtwork(Long id, Artwork updatedArtwork) {
        Optional<Artwork> existingArtwork = artworkRepository.findById(id);
        if (existingArtwork.isPresent()) {
            // Cập nhật các thuộc tính của artwork
            Artwork artwork = existingArtwork.get();
            artwork.setTitle(updatedArtwork.getTitle());
            artwork.setDescription(updatedArtwork.getDescription());
            artwork.setPrice(updatedArtwork.getPrice());
            artwork.setImageUrl(updatedArtwork.getImageUrl());
            return artworkRepository.save(artwork);
        } else {
            return null;
        }
    }

    // Xóa artwork theo ID
    public boolean deleteArtwork(Long id) {
        Optional<Artwork> existingArtwork = artworkRepository.findById(id);
        if (existingArtwork.isPresent()) {
            artworkRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
