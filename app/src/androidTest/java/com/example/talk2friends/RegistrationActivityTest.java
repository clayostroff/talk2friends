package com.example.talk2friends;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import java.util.Random;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class RegistrationActivityTest {

    @Before
    public void setUp() {
        // Launch the RegistrationActivity before each test
        ActivityScenario.launch(RegistrationActivity.class);
    }

    @Test
    public void testSuccessfulRegistration() {
        Espresso.onView(ViewMatchers.withId(R.id.registerButton))
                .perform(ViewActions.click());

        // Fill in the registration form with valid data
        Espresso.onView(ViewMatchers.withId(R.id.nameEditText))
                .perform(ViewActions.typeText("John Doe"), ViewActions.closeSoftKeyboard());

        StringBuilder email = new StringBuilder(9);
        Random random = new Random();
        String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 9; i++) {
            char randomChar = ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length()));
            email.append(randomChar);
        }
        email.append("@usc.edu");

        Espresso.onView(ViewMatchers.withId(R.id.emailEditText))
                .perform(ViewActions.typeText(email.toString()), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText))
                .perform(ViewActions.typeText("secure_password"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.ageEditText))
                .perform(ViewActions.typeText("25"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.nativeEnglishSpeakerCheckbox))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.sportsCheckbox))
                .perform(ViewActions.click());

        // Perform click on the register button
        Espresso.onView(ViewMatchers.withId(R.id.registerButton))
                .perform(ViewActions.click());

        // Sleep for a period (adjust the time based on your needs)
        try {
            Thread.sleep(4000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if the UserOptionsActivity is launched after successful registration
        Espresso.onView(ViewMatchers.withId(R.id.mainActivity)) // Replace with the actual ID of the view in UserOptionsActivity
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testEmptyFields() {
        // Perform click on the register button without entering any data
        Espresso.onView(ViewMatchers.withId(R.id.registerButton))
                .perform(ViewActions.click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Espresso.onView(ViewMatchers.withId(R.id.registrationActivity))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testInvalidEmail() {
        // Fill in the registration form with invalid email
        Espresso.onView(ViewMatchers.withId(R.id.nameEditText))
                .perform(ViewActions.typeText("John Doe"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.emailEditText))
                .perform(ViewActions.typeText("invalid_email"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText))
                .perform(ViewActions.typeText("password"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.ageEditText))
                .perform(ViewActions.typeText("25"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.nativeEnglishSpeakerCheckbox))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.sportsCheckbox))
                .perform(ViewActions.click());

        // Perform click on the register button
        Espresso.onView(ViewMatchers.withId(R.id.registerButton))
                .perform(ViewActions.click());

        // Sleep for a period (adjust the time based on your needs)
        try {
            Thread.sleep(2000); // 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Espresso.onView(ViewMatchers.withId(R.id.registrationActivity))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testShortPassword() {
        // Fill in the registration form with a short password
        Espresso.onView(ViewMatchers.withId(R.id.nameEditText))
                .perform(ViewActions.typeText("John Doe"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.emailEditText))
                .perform(ViewActions.typeText("john.doe@usc.edu"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.passwordEditText))
                .perform(ViewActions.typeText("short"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.ageEditText))
                .perform(ViewActions.typeText("25"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.nativeEnglishSpeakerCheckbox))
                .perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.sportsCheckbox))
                .perform(ViewActions.click());

        // Perform click on the register button
        Espresso.onView(ViewMatchers.withId(R.id.registerButton))
                .perform(ViewActions.click());

        // Sleep for a period (adjust the time based on your needs)
        try {
            Thread.sleep(5000); // 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Espresso.onView(ViewMatchers.withId(R.id.registrationActivity))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }

    // Add other tests as needed

}
