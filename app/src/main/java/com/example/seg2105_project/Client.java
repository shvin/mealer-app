package com.example.seg2105_project;
import java.util.ArrayList;

public class Client extends User{

    private int cardNumber;
    private String expiry;
    private int CVV;
    private ArrayList<Integer> orderHistory = new ArrayList<>();

    public Client(int id, String firstName, String lastName, String email, String password, String address, int cardNumber, String expiry, int CVV) {
        super(id, firstName, lastName, email, password, address);
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.CVV = CVV;
    }

    public void addToMealsOrder(int id){
        this.orderHistory.add(id);
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public ArrayList<Integer> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Integer> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
