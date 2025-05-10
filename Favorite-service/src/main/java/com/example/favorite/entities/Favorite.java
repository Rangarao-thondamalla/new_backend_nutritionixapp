package com.example.favorite.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "food_name", nullable = false, length = 255)
    private String foodName;

    @Column(name = "photo", length = 500)
    private String photo;

    @Column(name = "calories", nullable = false)
    private double calories;

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
}
