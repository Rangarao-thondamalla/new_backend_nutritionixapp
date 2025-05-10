package com.example.nutritionix.controller;

import com.example.nutritionix.entities.FoodItem;
import com.example.nutritionix.service.FoodItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FoodItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FoodItemService foodItemService;

    @InjectMocks
    private FoodItemController foodItemController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(foodItemController).build();
    }

    @Test
    void testGetAllFoodItems() throws Exception {
        List<FoodItem> mockFoodItems = Arrays.asList(
                new FoodItem(1, "Burger","https://example.com/burger.jpg", 450, 25, 40, 20 ),
                new FoodItem(2, "Pizza","https://example.com/pizza.jpg", 300, 12, 30, 10 )
        );

        when(foodItemService.getAllFoodItems()).thenReturn(mockFoodItems);

        mockMvc.perform(get("/api/food"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].foodName").value("Burger"))
                .andExpect(jsonPath("$[1].foodName").value("Pizza"));

        verify(foodItemService, times(1)).getAllFoodItems();
    }

    @Test
    void testGetFoodItemById() throws Exception {
        FoodItem mockFoodItem = new FoodItem(1, "Burger","https://example.com/burger.jpg", 450, 25, 40, 20 );

        when(foodItemService.getFoodItemById(1)).thenReturn(mockFoodItem);

        mockMvc.perform(get("/api/food/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foodName").value("Burger"))
                .andExpect(jsonPath("$.calories").value(450));

        verify(foodItemService, times(1)).getFoodItemById(1);
    }

    @Test
    void testAddFoodItem() throws Exception {
        FoodItem newFoodItem = new FoodItem(3, "Pasta","https://example.com/pasta.jpg", 350, 15, 50, 10 );

        when(foodItemService.addFoodItem(any(FoodItem.class))).thenReturn(newFoodItem);

        mockMvc.perform(post("/api/food")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newFoodItem)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.foodName").value("Pasta"))
                .andExpect(jsonPath("$.calories").value(350));

        verify(foodItemService, times(1)).addFoodItem(any(FoodItem.class));
    }

    @Test
    void testDeleteFoodItem() throws Exception {
        doNothing().when(foodItemService).deleteFoodItem(1);

        mockMvc.perform(delete("/api/food/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Food item deleted successfully!"));

        verify(foodItemService, times(1)).deleteFoodItem(1);
    }
}
