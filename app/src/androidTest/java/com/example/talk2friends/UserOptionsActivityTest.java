package com.example.talk2friends;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserOptionsActivityTest {

    @Before
    public void setUp() {
        // Initialize your test environment, for example, launching the UserOptionsActivity
        ActivityScenario.launch(UserOptionsActivity.class);
    }

    @Test
    public void testUserProfileButton() {
        // Click the user profile button
        Espresso.onView(withId(R.id.userProfileButton)).perform(ViewActions.click());

        // Check if UserProfileActivity is displayed
        Espresso.onView(withId(R.id.userProfileActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testMeetingManagerButton() {
        // Click the meeting manager button
        Espresso.onView(withId(R.id.meetingManagerButton)).perform(ViewActions.click());

        // Check if MeetingManagerActivity is displayed
        Espresso.onView(withId(R.id.meetingManagerActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testInviteFriendButton() {
        // Click the invite friend button
        Espresso.onView(withId(R.id.inviteFriendButton)).perform(ViewActions.click());

        // Check if InviteFriendActivity is displayed
        Espresso.onView(withId(R.id.inviteFriendActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testRecommenedFriendsButton() {
        // Click the recommend friends button
        Espresso.onView(withId(R.id.recommendFriendsButton)).perform(ViewActions.click());

        // Check if RecommendFriendsActivity is displayed
        Espresso.onView(withId(R.id.recommendFriendsActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void testLogoutButton() {
        // Click the logout button
        Espresso.onView(withId(R.id.logoutButton)).perform(ViewActions.click());

        // Check if MainActivity is displayed
        Espresso.onView(withId(R.id.mainActivity)).check(matches(isDisplayed()));
    }
}
