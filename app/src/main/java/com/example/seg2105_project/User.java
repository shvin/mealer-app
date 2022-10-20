package com.example.seg2105_project;

/**
 * Used to make an object of type User (Cook or Client) which can navigate in the app
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;

    /**
     * Empty constructor
     */
    public User(){
    }

    /**
     * Constructs a user with the given parameters. Classes Client and Cook can use this superclass to base their classes
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param address
     */
    public User(int id, String firstName, String lastName, String email, String password, String address){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    /**
     * returns the users ID
     * @return user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets users ID to parameter
     * @param id ID input
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * returns the first name of the User
     * @return user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the User first name to parameter
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * returns the USer last name
     * @return user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the User last name to parameter
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * returns User email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the User email to parameter
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns User password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the User password to parameter
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * returns User address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets the User address to parameter
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
