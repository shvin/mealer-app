package com.example.seg2105_project;
import java.util.ArrayList;

public class Cook extends User{
    private String description;
    private int mealsSold;
    private double averageRating;
    private ArrayList<Integer> ratings = new ArrayList<>();
    private ArrayList<Integer> menu = new ArrayList<>();
    private ArrayList<Integer> mealsRequest = new ArrayList<>();

    public Cook(){

    }

    public Cook(int id, String firstName, String lastName, String email, String password, String address, String description) {
        super(id, firstName, lastName, email, password, address);
        this.description = description;
        this.mealsSold = 0;
        this.averageRating = 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMealsSold() {
        return mealsSold;
    }

    public void setMealsSold(int mealsSold) {
        this.mealsSold = mealsSold;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }

    public ArrayList<Integer> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Integer> menu) {
        this.menu = menu;
    }

    public ArrayList<Integer> getMealsRequest() {
        return mealsRequest;
    }

    public void setMealsRequest(ArrayList<Integer> mealsRequest) {
        this.mealsRequest = mealsRequest;
    }
/*
    public void calculateAverageRating(){
        int sum = 0;
        for (int i = 0; i < ratings.size(); i++){
            sum += ratings.get(i);
        }
        averageRating = Math.round((sum/ratings.size())*10)/10.0;
    }*/


}
