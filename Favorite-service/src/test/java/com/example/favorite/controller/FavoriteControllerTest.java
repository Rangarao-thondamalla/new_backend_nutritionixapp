package com.example.favorite.controller;

import com.example.favorite.entities.Favorite;
import com.example.favorite.service.FavoriteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FavoriteController.class)
public class FavoriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FavoriteService favoriteService;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void testGetAllFavorites() throws Exception {
//        List<Favorite> favorites = Arrays.asList(new Favorite(1,"test","Item1",20), new Favorite(1,"test1","Item2",20));
//        when(favoriteService.getAllFavorites()).thenReturn(favorites);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/favorites"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("id").value(1))
//                .andExpect(jsonPath("name").value("Item1"))
//                .andExpect(jsonPath("id").value(2))
//                .andExpect(jsonPath("name").value("Item2"));
//    }

//    @Test
//    public void testAddFavorite() throws Exception {
//        Favorite favorite = new Favorite(1,"test","Item1",20);
//        when(favoriteService.addFavorite(Mockito.any(Favorite.class))).thenReturn(favorite);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/favorites")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(favorite)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("id").value(1))
//                .andExpect(jsonPath("name").value("New Item"));
//    }

    @Test
    public void testRemoveFavorite_Success() throws Exception {
        when(favoriteService.removeFavorite(1)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/favorites/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Favorite item removed successfully."));
    }

    @Test
    public void testRemoveFavorite_NotFound() throws Exception {
        when(favoriteService.removeFavorite(1)).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/favorites/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Favorite item not found."));
    }
}