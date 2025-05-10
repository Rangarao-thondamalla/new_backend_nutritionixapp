package com.example.favorite.controller;
import com.example.favorite.entities.Favorite;
import com.example.favorite.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")

public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    // Get all favorite items

    @GetMapping
    public ResponseEntity<List<Favorite>> getAllFavorites() {
        return ResponseEntity.ok(favoriteService.getAllFavorites());
    }

    // Add a favorite item

    @PostMapping
    public ResponseEntity<Favorite> addFavorite(@RequestBody Favorite favorite) {
        return ResponseEntity.ok(favoriteService.addFavorite(favorite));
    }

    // Remove a favorite item by ID

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFavorite(@PathVariable int id) {
        boolean deleted = favoriteService.removeFavorite(id);
        if (deleted) {
            return ResponseEntity.ok("Favorite item removed successfully.");
        } else {
            return ResponseEntity.status(404).body("Favorite item not found.");
        }
    }
}
