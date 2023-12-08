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
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddMeetingActivityTest {
    @Before
    public void setUp() {
        // Initialize your test environment, for example, launching the AddMeetingActivity
        ActivityScenario.launch(AddMeetingActivity.class);
    }

    @Test
    public void testAddMeetingButtonClick() {
        // Enter meeting details
        Espresso.onView(withId(R.id.linkEditText)).perform(ViewActions.typeText("https://zoom.us/meeting1"));
        Espresso.onView(withId(R.id.topicEditText)).perform(ViewActions.typeText("English Practice"));
        Espresso.onView(withId(R.id.timeEditText)).perform(ViewActions.typeText("5:00 PM"));
        Espresso.onView(withId(R.id.locationEditText)).perform(ViewActions.typeText("Online"));

        // Click the "Add" button
        Espresso.onView(withId(R.id.addButton)).perform(ViewActions.click());

        try {
            Thread.sleep(2000); // 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Check if the result is returned correctly
        Espresso.onView(withId(R.id.linkEditText)).check(matches(withText("https://zoom.us/meeting1"))); // Adjust with your actual resource IDs
        Espresso.onView(withId(R.id.topicEditText)).check(matches(withText("English Practice")));
        Espresso.onView(withId(R.id.timeEditText)).check(matches(withText("5:00 PM")));
        Espresso.onView(withId(R.id.locationEditText)).check(matches(withText("Online")));
    }

    // Add more tests as needed

}
