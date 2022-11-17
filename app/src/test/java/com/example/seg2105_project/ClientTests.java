package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.ArrayList;

public class ClientTests {
    Client client = new Client("123","Patrick","Meyer","pmeye@gmail.com","123456789","22 blue st","1233212342123212","1232","456");

    @Test
    public void checkEmail(){
        String expected = "pmeye@gmail.com";
        String actual = client.getEmail();
        assertEquals(actual,expected);

        client.setEmail("different1@gmail.com");
        assertEquals(client.getEmail(),"different1@gmail.com");
    }

    @Test
    public void checkCCNumberLength(){
        int expected = 16;
        int actual = client.getCardNumber().length();
        assertEquals(actual,expected);
    }

    @Test
    public void checkCvv(){
        int expected = 3;
        int actual = client.getCvv().length();
        assertEquals(actual,expected);
    }

    @Test
    public void checkExpiryDate(){
        int expected = 4;
        int actual = client.getExpiry().length();
        assertEquals(actual,expected);
    }

    @Test
    public void checkAddress(){
        String expected = "22 blue st";
        String actual = client.getAddress();
        assertEquals(actual,expected);

        client.setAddress("different address");
        assertEquals(client.getAddress(),"different address");
    }
}
