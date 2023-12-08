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
public class MeetingManagerActivityTest {

    @Before
    public void setUp() {
        // Initialize your test environment, for example, launching the MeetingManagerActivity
        ActivityScenario.launch(MeetingManagerActivity.class);
    }

    @Test
    public void testMeetingItemClick() {
        // Click the first meeting item in the list
        Espresso.onData(org.hamcrest.Matchers.anything())
                .inAdapterView(withId(R.id.meetingsListView))
                .atPosition(0)
                .perform(ViewActions.click());

        // Check if MeetingDetailsActivity is displayed
        Espresso.onView(withId(R.id.meetingDetailsActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testBackToMainButtonClick() {
        // Click the "Back to Main" button
        Espresso.onView(withId(R.id.backToMainButton)).perform(ViewActions.click());

    }

    @Test
    public void testAddMeetingButtonClick() {
        // Click the "Add Meeting" button
        Espresso.onView(withId(R.id.addMeetingButton)).perform(ViewActions.click());

        // Check if AddMeetingActivity is displayed
        Espresso.onView(withId(R.id.addMeetingActivity)).check(matches(isDisplayed()));
    }

    // Add more tests as needed

}
