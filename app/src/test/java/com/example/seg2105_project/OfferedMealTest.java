package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OfferedMealTest {
    Meal meal = new Meal("001", "999", "Pizza", "Lunch", "Italian", "Cheese, Tomato Sauce", "Dairy", 10.00, "Pizza with tomato sauce and cheese", true);

    @Test
    public void checkIfOffered(){
        Boolean expected = true;
        Boolean actual = meal.isOffered();
        assertEquals(actual,expected);
    }

    @Test
    public void checkIfOffered2(){
        meal.setOffered(false);
        Boolean expected = false;
        Boolean actual = meal.isOffered();
        assertEquals(actual,expected);
    }

    @Test
    public void checkCookID(){
        String expected = "999";
        String actual = meal.getCookID();
        assertEquals(actual,expected);
    }

    @Test
    public void checkMealType(){
        String expected = "Lunch";
        String actual = meal.getMealType();
        assertEquals(actual,expected);
    }
}
