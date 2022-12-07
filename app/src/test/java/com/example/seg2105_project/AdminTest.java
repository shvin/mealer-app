package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AdminTest {
    Admin admin = new Admin();

    @Test
    public void checkAdminID(){
        String expected = "111";
        String actual = admin.getAdminId();
        assertEquals(actual,expected);
    }

    @Test
    public void checkAdminPassword(){
        String expected = "111";
        String actual = admin.getAdminPassword();
        assertEquals(actual,expected);
    }
}
