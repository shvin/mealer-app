package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AddingMealTest {
    Meal meal = new Meal("123", "456", "Chicken Parm", "Dinner", "Italian", "Chicken, Parmesan, Tomato Sauce", "Dairy", 10.00, "Chicken Parmesan with tomato sauce and parmesan cheese", false);

    @Test
    public void checkMealName(){
        String expected = "Chicken Parm";
        String actual = meal.getName();
        assertEquals(actual,expected);
    }

    @Test
    public void checkMealType(){
        String expected = "Dinner";
        String actual = meal.getMealType();
        assertEquals(actual,expected);
    }

    @Test
    public void checkIfOffered(){
        Boolean expected = false;
        Boolean actual = meal.isOffered();
        assertEquals(actual,expected);
    }

    @Test
    public void checkMealID(){
        String expected = "123";
        String actual = meal.getId();
        assertEquals(actual,expected);
    }

    @Test
    public void checkCookID(){
        String expected = "456";
        String actual = meal.getCookID();
        assertEquals(actual,expected);
    }

    @Test
    public void checkMealCuisine(){
        String expected = "Italian";
        String actual = meal.getCuisineType();
        assertEquals(actual,expected);
    }
}
