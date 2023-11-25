package com.example.talk2friends;

import org.junit.Test;
import static org.junit.Assert.*;

public class EmailTest
{
    @Test
    public void testEmailInitialization() {
        Email email = new Email("student@usc.edu");
        assertEquals("student@usc.edu", email.getEmail());
    }

    @Test
    public void testSettersAndGetters() {
        Email email = new Email();
        email.setEmail("clay@usc.edu");
        assertEquals("clay@usc.edu", email.getEmail());
    }

    @Test
    void testValidEmail() {
        assertTrue(Email.isValidEmail("example@usc.edu"));
    }

    @Test
    void testInvalidEmailNoAtSymbol() {
        assertFalse(Email.isValidEmail("exampleusc.edu"));
    }

    @Test
    void testInvalidEmailNotUSCStudent() {
        assertFalse(Email.isValidEmail("example@gmail.com"));
    }

    @Test
    void testInvalidEmailEmptyString() {
        assertFalse(Email.isValidEmail(""));
    }
}