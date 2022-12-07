package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserTest {
    User user = new User("001", "John", "Doe", "jdizzle@gmail.com", "1234", "1234 Street");

    @Test
    public void checkFirstName() {
        String expected = "John";
        String actual = user.getFirstName();
        assertEquals(actual, expected);
    }

    @Test
    public void checkLastName() {
        String expected = "Doe";
        String actual = user.getLastName();
        assertEquals(actual, expected);
    }

    @Test
    public void checkEmail() {
        String expected = "jdizzle@gmail.com";
        String actual = user.getEmail();
        assertEquals(actual, expected);
    }

    @Test
    public void checkPassword() {
        String expected = "1234";
        String actual = user.getPassword();
        assertEquals(actual, expected);
    }

    @Test
    public void checkAddress() {
        String expected = "1234 Street";
        String actual = user.getAddress();
        assertEquals(actual, expected);
    }

    @Test
    public void checkID() {
        String expected = "001";
        String actual = user.getId();
        assertEquals(actual, expected);
    }
}