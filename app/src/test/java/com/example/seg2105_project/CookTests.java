package com.example.seg2105_project;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CookTests {

   Cook cook = new Cook("123","Rodrigo", "Hermano", "rodhe@gmail.com", "12345678", "55 hello st", "I am a cook");



   // Check if is banned
   @Test
   public void checkIfBanned(){
       Boolean expected = false;
       Boolean actual = cook.getBanned();
       assertEquals(actual,expected);
   }

   @Test
   // sets then ban and check if banned
   public void checkIfBanned2(){
      cook.setBanned(true);
      boolean expected = true;
      boolean actual = cook.getBanned();
      assertEquals(actual,expected);
   }

   @Test
   public void checkEmail(){
      String expected = "rodhe@gmail.com";
      String actual = cook.getEmail();
      assertEquals(actual,expected);

      cook.setEmail("rodhe2@gmail.com");
      String actual2 = cook.getEmail();
      String expected2 = "rodhe2@gmail.com";
      assertEquals(actual2,expected2);
   }

}
