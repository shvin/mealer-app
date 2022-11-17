package com.example.seg2105_project;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginPageTest {

    @Rule
    public ActivityTestRule<Register_Login_Page> activityRule = new ActivityTestRule<>(Register_Login_Page.class);

    @Test
    public void testLogin() {
        onView(withText("Welcome to Mealer")).check(matches(isDisplayed()));
    }
}
