package com.example.seg2105_project;

public class Order {
    String id;
    String cookId;
    String mealId;
    String clientId;
    String mealName;
    boolean pending;
    boolean approved;
    boolean rejected;

    public Order(){}

    public Order(String id, String cookId, String mealId, String clientId, String mealName, boolean pending, boolean approved, boolean rejected) {
        this.id = id;
        this.cookId = cookId;
        this.mealId = mealId;
        this.clientId = clientId;
        this.mealName = mealName;
        this.pending = pending;
        this.approved = approved;
        this.rejected = rejected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMealName(){
        return mealName;
    }

    public void setMealName(String meal){this.mealName = meal;}


    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public String toString(){
        String str = "Meal name: " + mealName + "\n" + "Status: ";
        if(pending) {
            str += "Pending";
        } else if(approved){
            str+= "Approved";
        } else if(rejected){
            str+= "Rejected";
        }

        return str;
    }
}
