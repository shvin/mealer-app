package com.example.seg2105_project;

import static org.junit.Assert.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

public class ClientCreditCardTest {
    DatabaseReference DR;

    @Test
    public void testClientCreditCardNumber1() {
        // test on a client with a credit card that has 16 digits
        DR = FirebaseDatabase.getInstance().getReference().child("Users/Clients/f65a3d55-68f3-4552-b7d8-d2f665dc46e3");
        String creditCardNumber = DR.child("cardNumber").toString();
        if (creditCardNumber.length() == 16) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    public void testClientCCV1() {
        // test on a client with a cvv that has 3 digits
        DR = FirebaseDatabase.getInstance().getReference().child("Users/Clients/1f2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d");
        String cvv = DR.child("cvv").toString();
        if (cvv.length() == 3) {
            assertTrue(true);
        } else {
            fail();
        }
    }
}
