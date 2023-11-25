package com.example.talk2friends;

import org.junit.Test;
import static org.junit.Assert.*;

class PasswordTest
{
    @Test
    void testValidPassword() {
        assertTrue(Password.isValidPassword("ValidPassword1"));
    }

    @Test
    void testInvalidPasswordTooShort() {
        assertFalse(Password.isValidPassword("Abc1"));
    }

    @Test
    void testInvalidPasswordTooLong() {
        assertFalse(Password.isValidPassword("LongPasswordThatExceeds32CharacterLimit"));
    }

    @Test
    void testInvalidPasswordNoUpperCase() {
        assertFalse(Password.isValidPassword("invalidpassword1"));
    }

    @Test
    void testInvalidPasswordNoLowerCase() {
        assertFalse(Password.isValidPassword("INVALIDPASSWORD1"));
    }

    @Test
    void testInvalidPassword_NoNumbers() {
        assertFalse(Password.isValidPassword("InvalidPassword"));
    }

    @Test
    void testInvalidPassword_Null() {
        assertFalse(Password.isValidPassword(null));
    }
}