package com.example.nutritionix.service;


import com.example.nutritionix.Repository.FoodItemRepository;
import com.example.nutritionix.entities.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public FoodItem getFoodItemById(int id) {
        return foodItemRepository.findById(id).orElse(null);
    }

    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public void deleteFoodItem(int id) {
        foodItemRepository.deleteById(id);
    }
}
