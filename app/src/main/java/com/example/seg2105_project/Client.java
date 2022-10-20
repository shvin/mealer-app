package com.example.seg2105_project;
import java.util.ArrayList;

/**
 * Client is a subclass of User and inherits it's functions and attributes. Is used to create a Client object
 */
public class Client extends User {

    private String cardNumber;
    private String expiry;
    private String cvv;
    private ArrayList<Integer> orderHistory = new ArrayList<>();

    /**
     * Empty constructor
     */
    public Client(){
    }

    /**
     * Constructs Client based on parameters given
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param address
     * @param cardNumber
     * @param expiry
     * @param cvv
     * @param orderHistory
     */
    public Client(int id, String firstName, String lastName, String email, String password, String address, String cardNumber, String expiry, String cvv, ArrayList<Integer> orderHistory) {
        super(id, firstName, lastName, email, password, address);
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.orderHistory = orderHistory;
    }

    /**
     * returns Client card number
     * @return cardNumber
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the Client card number to parameter
     * @param cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * returns the expiry date of credit card
     * @return expiry
     */
    public String getExpiry() {
        return expiry;
    }

    /**
     * sets the Client credit card expiry date to parameter
     * @param expiry
     */
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    /**
     * returns Client CVV pin
     * @return cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Sets the Client CVV pin to parameter
     * @param cvv
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

//    public void addToMealsOrder(int id){
//        this.orderHistory.add(id);
//        }
//    public ArrayList<Integer> getOrderHistory() {
//        return orderHistory;
//    }
//
//    public void setOrderHistory(ArrayList<Integer> orderHistory) {
//        this.orderHistory = orderHistory;
//    }
}
