package com.example.webbanhtranh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.webbanhtranh.models.Artwork;
import com.example.webbanhtranh.service.ArtworkService;

import java.util.List;

@Controller
@RequestMapping("/artworks")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @GetMapping
    public String listArtworks(Model model) {
        List<Artwork> artworks = artworkService.getAllArtworks();
        model.addAttribute("artworks", artworks);
        return "artworks";
    }

    @GetMapping("/{id}")
    public String viewArtwork(@PathVariable Long id, Model model) {
        Artwork artwork = artworkService.getArtworkById(id);
        model.addAttribute("artwork", artwork);
        return "artwork-details";  // View for artwork details
    }


}
