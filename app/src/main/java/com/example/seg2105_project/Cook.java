package com.example.seg2105_project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Cook is a subclass of User and inherits it's functions and attributes. Is used to create a Cook object
 */
public class Cook extends User{
    private String description;
    private int mealsSold;
    private double averageRating;
    private boolean banned;
    private boolean suspended;
    private int daysSuspended;
    private double totalRatings;
    private int numOfRatings;
    private ArrayList<Meal> menu = new ArrayList<>();
    private ArrayList<Meal> mealsRequest = new ArrayList<>();

    /**
     * Empty constructor
     */
    public Cook(){

    }

    /**
     * Constructs Cook based on parameters given
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param address
     * @param description
     */
    public Cook(String id, String firstName, String lastName, String email, String password, String address, String description) {
        super(id, firstName, lastName, email, password, address);
        this.description = description;
        this.mealsSold = 0;
        this.averageRating = 0;
        this.suspended = false;
        this.daysSuspended = 0;
        this.banned = false;
        totalRatings = 0;
        numOfRatings = 0;
    }

    public Cook(String id, String firstName, String lastName, String email, String password, String address, String description, int mealsSold, double averageRating, boolean suspended, int daysSuspended, boolean banned) {
        super(id, firstName, lastName, email, password, address);
        this.description = description;
        this.mealsSold = mealsSold;
        this.averageRating = averageRating;
        this.suspended = suspended;
        this.daysSuspended = daysSuspended;
        this.banned = banned;
        totalRatings = 0;
        numOfRatings = 0;
    }



    /**
     * returns the cook's description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets the cook's description to parameter
     * @param description
     */
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

    public boolean getSuspended(){
        return suspended;
    }

    public void setSuspended(boolean suspension){
        suspended = suspension;
    }

    public int getDaysSuspended(){
        return daysSuspended;
    }

    public void setDaysSuspended(int numDays){
        daysSuspended = numDays;
    }

    public boolean getBanned(){
        return banned;
    }

    public void setBanned(boolean ban){
        banned = ban;
    }

    public double getTotalRatings() {
        return totalRatings;
    }

    public void addToTotalRatings(double totalRatings) {
        this.totalRatings+= this.totalRatings + totalRatings;
    }

    public int getNumOfRatings(){return numOfRatings;}

    public void incrementNumOfRatings(){
        this.numOfRatings+=1;
    }

    public double calculateAverageRating(){
        double average = 0;
        if (numOfRatings == 0 || totalRatings == 0) return 0.0;
        else {
            average = totalRatings/numOfRatings;
        }
        return average;
    }

}
