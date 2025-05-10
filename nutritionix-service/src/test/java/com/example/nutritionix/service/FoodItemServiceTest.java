package com.example.nutritionix.service;

import com.example.nutritionix.Repository.FoodItemRepository;
import com.example.nutritionix.entities.FoodItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodItemServiceTest {

    @Mock
    private FoodItemRepository foodItemRepository;

    @InjectMocks
    private FoodItemService foodItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFoodItems() {
        List<FoodItem> mockFoodItems = Arrays.asList(
                new FoodItem(1, "Burger", "https://example.com/burger.jpg",450, 25, 40, 20 ),
                new FoodItem(2, "Pizza","https://example.com/pizza.jpg", 300, 12, 30, 10)
        );

        when(foodItemRepository.findAll()).thenReturn(mockFoodItems);

        List<FoodItem> result = foodItemService.getAllFoodItems();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Burger", result.get(0).getFoodName());
        assertEquals("Pizza", result.get(1).getFoodName());

        verify(foodItemRepository, times(1)).findAll();
    }

    @Test
    void testGetFoodItemById_Found() {
        FoodItem mockFoodItem = new FoodItem(1, "Burger","https://example.com/burger.jpg", 450, 25, 40, 20);

        when(foodItemRepository.findById(1)).thenReturn(Optional.of(mockFoodItem));

        FoodItem result = foodItemService.getFoodItemById(1);

        assertNotNull(result);
        assertEquals("Burger", result.getFoodName());
        assertEquals(450, result.getCalories());

        verify(foodItemRepository, times(1)).findById(1);
    }

    @Test
    void testGetFoodItemById_NotFound() {
        when(foodItemRepository.findById(1)).thenReturn(Optional.empty());

        FoodItem result = foodItemService.getFoodItemById(1);

        assertNull(result);
        verify(foodItemRepository, times(1)).findById(1);
    }

    @Test
    void testAddFoodItem() {
        FoodItem newFoodItem = new FoodItem(3, "Pasta","https://example.com/pasta.jpg", 350, 15, 50, 10);

        when(foodItemRepository.save(any(FoodItem.class))).thenReturn(newFoodItem);

        FoodItem result = foodItemService.addFoodItem(newFoodItem);

        assertNotNull(result);
        assertEquals("Pasta", result.getFoodName());
        assertEquals(350, result.getCalories());

        verify(foodItemRepository, times(1)).save(any(FoodItem.class));
    }

    @Test
    void testDeleteFoodItem() {
        doNothing().when(foodItemRepository).deleteById(1);

        foodItemService.deleteFoodItem(1);

        verify(foodItemRepository, times(1)).deleteById(1);
    }
}
