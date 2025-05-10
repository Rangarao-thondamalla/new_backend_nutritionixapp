package com.example.nutritionix.controller;


import com.example.nutritionix.entities.FoodItem;
import com.example.nutritionix.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public FoodItem getFoodItemById(@PathVariable int id) {
        return foodItemService.getFoodItemById(id);
    }

    @PostMapping
    public FoodItem addFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.addFoodItem(foodItem);
    }

    @DeleteMapping("/{id}")
    public String deleteFoodItem(@PathVariable int id) {
        foodItemService.deleteFoodItem(id);
        return "Food item deleted successfully!";
    }
}
