package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CookSuspensionTest {
    Cook cook = new Cook("123", "Kanye", "East", "notkanyewest@hotmail.com", "password", "1234 Kanye Ave", "I am a cook");

    @Test
    public void checkIfBanned(){
        Boolean expected = false;
        Boolean actual = cook.getBanned();
        assertEquals(actual,expected);
    }

    @Test
    // ban kanye east and check if hes banned
    public void checkIfBanned2(){
        cook.setBanned(true);
        boolean expected = true;
        boolean actual = cook.getBanned();
        assertEquals(actual,expected);
    }

    @Test
    // unban kanye east and check if hes banned
    public void checkIfBanned3(){
        cook.setBanned(false);
        boolean expected = false;
        boolean actual = cook.getBanned();
        assertEquals(actual,expected);
    }

    @Test
    // check that the days of suspension is 0
    public void checkDaysOfSuspension(){
        int expected = 0;
        int actual = cook.getDaysSuspended();
        assertEquals(actual,expected);
    }

    @Test
    // check that the days of suspension is 0
    public void checkDaysOfSuspension2(){
        cook.setDaysSuspended(5);
        int expected = 5;
        int actual = cook.getDaysSuspended();
        assertEquals(actual,expected);
    }
}
