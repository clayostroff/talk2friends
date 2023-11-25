package com.example.talk2friends;

import java.io.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email implements Serializable
{
    private String email;

    private static final String EMAIL_PATTERN =  "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public Email() {

    }

    public Email(String email) {
        this.email = email;
    };

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() && (email.substring(email.length() - 8)).equals("@usc.edu")) {
            return true;
        } else {
            return false;
        }
    }
}