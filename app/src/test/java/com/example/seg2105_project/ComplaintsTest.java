package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ComplaintsTest {
    Complaint complaint = new Complaint("000", "food had snails in it", "001");

    @Test
    public void checkComplaint(){
        String expected = "food had snails in it";
        String actual = complaint.getDescription();
        assertEquals(actual,expected);
    }

    @Test
    public void checkComplaint2(){
        complaint.setDescription("food had snails in it and was cold");
        String expected = "food had snails in it and was cold";
        String actual = complaint.getDescription();
        assertEquals(actual,expected);
    }

    @Test
    public void checkCookID(){
        String expected = "001";
        String actual = complaint.getCookID();
        assertEquals(actual,expected);
    }

    @Test
    public void checkCookID2(){
        complaint.setCookID("002");
        String expected = "002";
        String actual = complaint.getCookID();
        assertEquals(actual,expected);
    }

    @Test
    public void checkID(){
        String expected = "000";
        String actual = complaint.getId();
        assertEquals(actual,expected);
    }

    @Test
    public void checkID2(){
        complaint.setId("001");
        String expected = "001";
        String actual = complaint.getId();
        assertEquals(actual,expected);
    }
}
