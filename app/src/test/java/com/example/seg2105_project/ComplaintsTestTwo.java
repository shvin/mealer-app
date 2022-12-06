package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComplaintsTestTwo {
    Complaint complaint = new Complaint("001", "food was not cooked properly", "001");

    @Test
    public void checkComplaint(){
        String expected = "food was not cooked properly";
        String actual = complaint.getDescription();
        assertEquals(actual,expected);
    }

    @Test
    public void checkComplaint2(){
        complaint.setDescription("food was not cooked properly and was cold");
        String expected = "food was not cooked properly and was cold";
        String actual = complaint.getDescription();
        assertEquals(actual,expected);
    }

    @Test
    public void checkCookID(){
        complaint.setCookID("002");
        String expected = "002";
        String actual = complaint.getCookID();
        assertEquals(actual,expected);
    }

    @Test
    public void checkID(){
        complaint.setId("002");
        String expected = "002";
        String actual = complaint.getId();
        assertEquals(actual,expected);
    }
}
