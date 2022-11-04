package com.example.seg2105_project;

import static org.junit.Assert.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.junit.Test;

public class CookBannedTest {

//    @Test
//    public void testCookBanned1() {
//        // test on an unbanned cook
//        DatabaseReference DR1 = FirebaseDatabase.getInstance().getReference().child("Users/Cooks/76c62824-87db-41fc-b358-265fa12b5cf5");
//        String banned = DR1.child("banned").toString();
//        assertEquals("false", banned);
//    }
//
//    @Test
//    public void testCookBanned2() {
//        // test on a banned cook
//        DatabaseReference DR2 = FirebaseDatabase.getInstance().getReference().child("Users/Cooks/97320e01-cba2-4d23-b0d8-588d8744a3cb");
//        String banned = DR2.child("banned").toString();
//        assertEquals("true", banned);
//    }
//
//    @Test
//    public void testCookBannedAndSuspended() {
//        // test on a banned cook but unsuspended role
//        DatabaseReference DR3 = FirebaseDatabase.getInstance().getReference().child("Users/Cooks/97320e01-cba2-4d23-b0d8-588d8744a3cb");
//        String banned = DR3.child("banned").toString();
//        DatabaseReference DR4 = FirebaseDatabase.getInstance().getReference().child("Users/Cooks/97320e01-cba2-4d23-b0d8-588d8744a3cb");
//        String suspended = DR4.child("suspended").toString();
//        if (banned.equals("true") && suspended.equals("false")) {
//            assertTrue(true);
//        } else {
//            fail();
//        }
//    }
}
