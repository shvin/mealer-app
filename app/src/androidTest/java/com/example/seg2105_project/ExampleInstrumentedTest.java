package com.example.seg2105_project;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.seg2105_project", appContext.getPackageName());

        // test on a client with a credit card that has 16 digits
        DatabaseReference DR = FirebaseDatabase.getInstance().getReference().child("Users/Clients/f65a3d55-68f3-4552-b7d8-d2f665dc46e3");
        String creditCardNumber = DR.child("cardNumber").toString();
        if (creditCardNumber.length() == 16) {
            assertTrue(true);
        } else {
            fail();
        }
    }
}