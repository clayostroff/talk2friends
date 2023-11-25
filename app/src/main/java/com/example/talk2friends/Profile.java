package com.example.talk2friends;

import java.io.Serializable;

public class Profile implements Serializable {
    private String name;
    private String email;
    private String password;
    private String age;
    private boolean isNativeEnglishSpeaker;
    private boolean isNativeSpanishSpeaker;
    private String interests;

    public Profile() {

    }

    public Profile(String name, String email, String password, String age, boolean isNativeEnglishSpeaker, boolean isNativeSpanishSpeaker, String interests) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.isNativeEnglishSpeaker = isNativeEnglishSpeaker;
        this.isNativeSpanishSpeaker = isNativeSpanishSpeaker;
        this.interests = interests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isNativeEnglishSpeaker() {
        return isNativeEnglishSpeaker;
    }

    public void setNativeEnglishSpeaker(boolean nativeEnglishSpeaker) {
        isNativeEnglishSpeaker = nativeEnglishSpeaker;
    }

    public boolean isNativeSpanishSpeaker() {
        return isNativeSpanishSpeaker;
    }

    public void setNativeSpanishSpeaker(boolean nativeSpanishSpeaker) {
        isNativeSpanishSpeaker = nativeSpanishSpeaker;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }
}
