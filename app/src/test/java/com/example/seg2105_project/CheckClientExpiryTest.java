package com.example.seg2105_project;

import static org.junit.Assert.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

public class CheckClientExpiryTest {

//    @Test
//    public void testClientCreditCardNumber1() {
//        // test on a client whose expiry date has not passed
//        DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference().child("Users/Clients/f65a3d55-68f3-4552-b7d8-d2f665dc46e3");
//        String expiryDate = DR1.child("expiry").toString();
//        String month = expiryDate.substring(0, 2);
//        String year = expiryDate.substring(2, 4);
//        int monthInt = Integer.parseInt(month);
//        int yearInt = Integer.parseInt(year);
//        if (yearInt > 22) {
//            assertTrue(true);
//        } else if (yearInt == 22 && monthInt >= 11) {
//            assertTrue(true);
//        } else {
//            fail();
//        }
//    }
//
//    @Test
//    public void testClientCreditCardNumber2() {
//        // test on a client whose expiry date has passed
//        DatabaseReference DR2 = FirebaseDatabase.getInstance().getReference().child("Users/Clients/e61ad2f9-540f-4d9f-a9a4-e928eb28927a");
//        String expiryDate = DR2.child("expiry").toString();
//        String month = expiryDate.substring(0, 2);
//        String year = expiryDate.substring(2, 4);
//        int monthInt = Integer.parseInt(month);
//        int yearInt = Integer.parseInt(year);
//        if (yearInt < 22) {
//            assertTrue(true);
//        } else if (yearInt == 22 && monthInt <= 11) {
//            assertTrue(true);
//        } else {
//            fail();
//        }
//    }
}