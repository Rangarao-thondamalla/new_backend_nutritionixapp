package com.example.favorite.service;

import com.example.favorite.entities.Favorite;
import com.example.favorite.repository.FavoriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;

    @InjectMocks
    private FavoriteService favoriteService;

    private Favorite favorite1;
    private Favorite favorite2;

    @BeforeEach
    void setUp() {
        favorite1 = new Favorite(1, "Pizza", "pizza.jpg", 250);
        favorite2 = new Favorite(2, "Burger", "burger.jpg", 300);
    }

    @Test
    public void testGetAllFavorites() {
        List<Favorite> favorites = Arrays.asList(favorite1, favorite2);
        when(favoriteRepository.findAll()).thenReturn(favorites);

        List<Favorite> result = favoriteService.getAllFavorites();

        assertEquals(2, result.size());
        assertEquals("Pizza", result.get(0).getFoodName());
        assertEquals("Burger", result.get(1).getFoodName());
        verify(favoriteRepository, times(1)).findAll();
    }

    @Test
    public void testAddFavorite() {
        when(favoriteRepository.save(Mockito.any(Favorite.class))).thenReturn(favorite1);

        Favorite savedFavorite = favoriteService.addFavorite(favorite1);

        assertNotNull(savedFavorite);
        assertEquals(1, savedFavorite.getId());
        assertEquals("Pizza", savedFavorite.getFoodName());
        verify(favoriteRepository, times(1)).save(favorite1);
    }

    @Test
    public void testRemoveFavorite_Success() {
        when(favoriteRepository.findById(1)).thenReturn(Optional.of(favorite1));
        doNothing().when(favoriteRepository).deleteById(1);

        boolean isRemoved = favoriteService.removeFavorite(1);

        assertTrue(isRemoved);
        verify(favoriteRepository, times(1)).findById(1);
        verify(favoriteRepository, times(1)).deleteById(1);
    }

    @Test
    public void testRemoveFavorite_NotFound() {
        when(favoriteRepository.findById(1)).thenReturn(Optional.empty());

        boolean isRemoved = favoriteService.removeFavorite(1);

        assertFalse(isRemoved);
        verify(favoriteRepository, times(1)).findById(1);
        verify(favoriteRepository, never()).deleteById(anyInt());
    }
}
