package com.example.seg2105_project;

import java.util.ArrayList;

public class Meal {
    private int id;
    private String name;
    private MEAL_TYPE mealType;
    private CUISINE_TYPE cuisineType;
    private String ingredients;
    private String allergens;
    private String price;
    private String description;

    public Meal(int id, String name, MEAL_TYPE mealType, CUISINE_TYPE cuisineType, String ingredients, String allergens, String price, String description) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MEAL_TYPE getMealType() {
        return mealType;
    }

    public void setMealType(MEAL_TYPE mealType) {
        this.mealType = mealType;
    }

    public CUISINE_TYPE getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CUISINE_TYPE cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
