package com.example.talk2friends;

import android.content.Intent;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Before
    public void setUp() {
        // Initialize your test environment, for example, launching the LoginActivity
        ActivityScenario.launch(LoginActivity.class);
    }

    @Test
    public void testLoginSuccess() {
        // Enter valid email and password, then click the login button
        Espresso.onView(withId(R.id.usernameEditText)).perform(ViewActions.typeText("gennarel@usc.edu"));
        Espresso.onView(withId(R.id.passwordEditText)).perform(ViewActions.typeText("ffffff"));
        Espresso.onView(withId(R.id.loginButton)).perform(ViewActions.click());

        try {
            Thread.sleep(4000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the UserOptionsActivity is displayed after successful login
        Espresso.onView(withId(R.id.userOptionsActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginFailure() {
        // Enter invalid email and password, then click the login button
        Espresso.onView(withId(R.id.usernameEditText)).perform(ViewActions.typeText("invalid@email.com"));
        Espresso.onView(withId(R.id.passwordEditText)).perform(ViewActions.typeText("wrongpassword"));
        Espresso.onView(withId(R.id.loginButton)).perform(ViewActions.click());
        try {
            Thread.sleep(2000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Espresso.onView(withId(R.id.loginActivity)).check(matches(isDisplayed()));
    }

}
