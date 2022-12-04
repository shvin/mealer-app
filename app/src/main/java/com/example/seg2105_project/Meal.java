package com.example.seg2105_project;

import java.util.ArrayList;

/**
 * The Class Meal initializes a meal object
 */
public class Meal {
    private String id;
    private String cookID;
    private String name;
    private String mealType;
    private String cuisineType;
    private String ingredients;
    private String allergens;
    private double price;
    private String description;
    private boolean offered;
    private boolean available;

    public Meal(){}

    public Meal(String id, String cookID, String name, String mealType, String cuisineType, String ingredients, String allergens, double price, String description) {
        this.id = id;
        this.cookID = cookID;
        this.name = name;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.offered = true;
        this.available = true;
    }

    public Meal(String id, String cookID, String name, String mealType, String cuisineType, String ingredients, String allergens, double price, String description, Boolean offered) {
        this.id = id;
        this.cookID = cookID;
        this.name = name;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.offered = offered;
        this.available = true;
    }

    public Meal(String id, String cookID, String name, String mealType, String cuisineType, String ingredients, String allergens, double price, String description, Boolean offered, Boolean available) {
        this.id = id;
        this.cookID = cookID;
        this.name = name;
        this.mealType = mealType;
        this.cuisineType = cuisineType;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.offered = offered;
        this.available = available;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCookID() {return cookID; }

    public void setCookID(String cookID) { this.cookID = cookID; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOffered() {
        return offered;
    }

    public void setOffered(boolean offered) {
        this.offered = offered;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString(){
        return "Meal name: " + name + "\n" + " Price: " + price;
    }
}
