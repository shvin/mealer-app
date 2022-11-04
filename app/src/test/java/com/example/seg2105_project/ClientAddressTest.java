package com.example.seg2105_project;

import static org.junit.Assert.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

public class ClientAddressTest {
    DatabaseReference DR;

    @Test
    public void checkClientAddress1() {
        // checks a client's address to make sure it contains numbers and letters
        DR = FirebaseDatabase.getInstance().getReference().child("Users/Clients/0f758e52-0858-49f8-a859-75dedb13944d");
        String address = DR.child("address").toString();
        if (address.matches(".*\\d.*") && address.matches(".*[a-zA-Z]+.*")) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    public void checkClientAddress2() {
        // checks a client's address to make sure it contains numbers and letters
        DR = FirebaseDatabase.getInstance().getReference().child("Users/Clients/f65a3d55-68f3-4552-b7d8-d2f665dc46e3");
        String address = DR.child("address").toString();
        if (address.matches(".*\\d.*") && address.matches(".*[a-zA-Z]+.*")) {
            assertTrue(true);
        } else {
            fail();
        }
    }
}