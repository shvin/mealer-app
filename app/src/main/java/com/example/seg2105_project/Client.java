package com.example.seg2105_project;
import java.util.ArrayList;

public class Client extends User {

    private String cardNumber;
    private String expiry;
    private String cvv;
    private ArrayList<Integer> orderHistory = new ArrayList<>();

    public Client(){
    }

    public Client(int id, String firstName, String lastName, String email, String password, String address, String cardNumber, String expiry, String cvv, ArrayList<Integer> orderHistory) {
        super(id, firstName, lastName, email, password, address);
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.orderHistory = orderHistory;
    }

    //public void addToMealsOrder(int id){
        //this.orderHistory.add(id);
    //}


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public ArrayList<Integer> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(ArrayList<Integer> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
