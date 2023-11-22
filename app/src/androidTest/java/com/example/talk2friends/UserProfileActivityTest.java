package com.example.talk2friends;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserProfileActivityTest {

    @Before
    public void setUp() {
        // Launch the UserProfileActivity
        ActivityScenario.launch(UserProfileActivity.class);
    }

    @Test
    public void testUserProfileDisplayed() {
        // Check if user profile data is displayed
        Espresso.onView(withId(R.id.textViewName)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.textViewAge)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.textViewInterests)).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testEditProfileButtonClick() {
        // Click the "Edit Profile" button
        Espresso.onView(withId(R.id.buttonEditProfile)).perform(ViewActions.click());

        // Check if UpdateProfileActivity is displayed
        Espresso.onView(withId(R.id.updateProfileActivity)).check(ViewAssertions.matches(isDisplayed()));
    }

    // Add more tests as needed
}
