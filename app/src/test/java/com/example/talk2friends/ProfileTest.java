package com.example.talk2friends;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProfileTest {

    @Test
    public void testProfileInitialization() {
        Profile profile = new Profile("John Doe", "john@example.com", "password123", "25", true, false, "Programming");

        assertEquals("John Doe", profile.getName());
        assertEquals("john@example.com", profile.getEmail());
        assertEquals("password123", profile.getPassword());
        assertEquals("25", profile.getAge());
        assertTrue(profile.isNativeEnglishSpeaker());
        assertFalse(profile.isNativeSpanishSpeaker());
        assertEquals("Programming", profile.getInterests());
    }

    @Test
    public void testSettersAndGetters() {
        Profile profile = new Profile();

        profile.setName("Jane Doe");
        profile.setEmail("jane@example.com");
        profile.setPassword("newpassword");
        profile.setAge("30");
        profile.setNativeEnglishSpeaker(false);
        profile.setNativeSpanishSpeaker(true);
        profile.setInterests("Reading");

        assertEquals("Jane Doe", profile.getName());
        assertEquals("jane@example.com", profile.getEmail());
        assertEquals("newpassword", profile.getPassword());
        assertEquals("30", profile.getAge());
        assertFalse(profile.isNativeEnglishSpeaker());
        assertTrue(profile.isNativeSpanishSpeaker());
        assertEquals("Reading", profile.getInterests());
    }
}

