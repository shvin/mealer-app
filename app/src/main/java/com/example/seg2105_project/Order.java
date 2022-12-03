package com.example.seg2105_project;

public class Order {
    int cookId;
    int mealId;
    int clientId;
    boolean pending;
    boolean approved;
    boolean rejected;

    public Order(){}

    public Order(int cookId, int mealId, int clientId, boolean pending, boolean approved, boolean rejected) {
        this.cookId = cookId;
        this.mealId = mealId;
        this.clientId = clientId;
        this.pending = true;
        this.approved = false;
        this.rejected = false;
    }

    public int getCookId() {
        return cookId;
    }

    public void setCookId(int cookId) {
        this.cookId = cookId;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

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
}
