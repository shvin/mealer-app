package com.example.seg2105_project;

import static org.junit.Assert.*;

import org.junit.Test;

public class CookCreationTest {
    @Test
    public void cookCreationTest1() {
        Cook cook = new Cook();
        cook.setFirstName("John");
        assertEquals("John", cook.getFirstName());
    }

    @Test
    public void cookCreationTest2() {
        Cook cook = new Cook();
        cook.setLastName("Smith");
        assertEquals("Smith", cook.getLastName());
    }

    @Test
    public void cookCreationTest3(){
        Cook cook = new Cook();
        cook.setMealsSold(0);
        assertEquals(0, cook.getMealsSold());
    }
}