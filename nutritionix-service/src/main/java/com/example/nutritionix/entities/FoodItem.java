package com.example.nutritionix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food_items")  // Maps to the MySQL table
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private int id;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "photo")
    private String photo;

    @Column(name = "calories", nullable = false)
    private double calories;

    @Column(name = "protein", nullable = false)
    private double protein;

    @Column(name = "carbs", nullable = false)
    private double carbs;

    @Column(name = "fat", nullable = false)
    private double fat;

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public int getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getPhoto() {
        return photo;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }
}


