package com.example.favorite.service;


import com.example.favorite.entities.Favorite;
import com.example.favorite.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    // Fetch all favorite items
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    // Add a new favorite item
    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    // Remove a favorite item by ID
    public boolean removeFavorite(int id) {
        Optional<Favorite> favorite = favoriteRepository.findById(id);
        if (favorite.isPresent()) {
            favoriteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
