package com.example.talk2friends;

import java.io.Serializable;

import java.util.regex.Pattern;

public class Password implements Serializable
{
    private String password;

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,32}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public Password(String password) {
        this.password = password;
    };

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return pattern.matcher(password).matches();
    }
}