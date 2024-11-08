package com.example.webbanhtranh.service;

import com.example.webbanhtranh.models.Artwork;
import com.example.webbanhtranh.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtworkService {
    @Autowired
    private ArtworkRepository artworkRepository;

    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    public Artwork getArtworkById(Long id) {
        return artworkRepository.findById(id).orElse(null);
    }

    public void saveArtwork(Artwork artwork) {
        artworkRepository.save(artwork);
    }
}
