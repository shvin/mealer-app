package com.example.seg2105_project;

import static org.junit.Assert.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.junit.Test;

public class CookBannedTest {
    DatabaseReference DR;

    @Test
    public void testCookBanned1() {
        // test on an unbanned cook
        DR = FirebaseDatabase.getInstance().getReference().child("Users/Cooks/76c62824-87db-41fc-b358-265fa12b5cf5");
        String banned = DR.child("banned").toString();
        assertEquals("false", banned);
    }

    @Test
    public void testCookBanned2() {
        // test on a banned cook
        DR = FirebaseDatabase.getInstance().getReference().child("Users/Cooks/97320e01-cba2-4d23-b0d8-588d8744a3cb");
        String banned = DR.child("banned").toString();
        assertEquals("true", banned);
    }

    @Test
    public void testCookBannedAndSuspended() {
        // test on a banned cook but unsuspended role
        DR = FirebaseDatabase.getInstance().getReference().child("Users/Cooks/97320e01-cba2-4d23-b0d8-588d8744a3cb");
        String banned = DR.child("banned").toString();
        String suspended = DR.child("suspended").toString();
        if (banned.equals("true") && suspended.equals("false")) {
            assertTrue(true);
        } else {
            fail();
        }
    }
}
