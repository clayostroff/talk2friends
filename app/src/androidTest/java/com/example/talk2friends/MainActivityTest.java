package com.example.talk2friends;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Before
    public void setUp() {
        // Launch the MainActivity before each test
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testRegisterButton() {
        // Click on the register button
        Espresso.onView(ViewMatchers.withId(R.id.registerButton)).perform(ViewActions.click());

        // Check if the RegistrationActivity is displayed
        Espresso.onView(ViewMatchers.withId(R.id.nameEditText)) // Assuming R.id.nameEditText is in RegistrationActivity
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testLoginButton() {
        // Click on the login button
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click());

        // Check if the LoginActivity is displayed
        Espresso.onView(ViewMatchers.withId(R.id.usernameEditText)) // Assuming R.id.usernameEditText is in LoginActivity
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    // Add more tests based on your MainActivity logic

}
