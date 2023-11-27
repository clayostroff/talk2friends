package com.example.talk2friends;

import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordTest
{
    @Test
    public void testValidPassword() {
        assertTrue(Password.isValidPassword("ValidPassword1"));
    }

    @Test
    public void testInvalidPasswordTooShort() {
        assertFalse(Password.isValidPassword("Abc1"));
    }

    @Test
    public void testInvalidPasswordTooLong() {
        assertFalse(Password.isValidPassword("LongPasswordThatExceeds32CharacterLimit"));
    }

    @Test
    public void testInvalidPasswordNoUpperCase() {
        assertFalse(Password.isValidPassword("invalidpassword1"));
    }

    @Test
    public void testInvalidPasswordNoLowerCase() {
        assertFalse(Password.isValidPassword("INVALIDPASSWORD1"));
    }

    @Test
    public void testInvalidPassword_NoNumbers() {
        assertFalse(Password.isValidPassword("InvalidPassword"));
    }

    @Test
    public void testInvalidPassword_Null() {
        assertFalse(Password.isValidPassword(null));
    }
}