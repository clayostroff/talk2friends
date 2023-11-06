package com.example.talk2friends;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MeetingManager {
    private List<Meeting> meetings;

    public MeetingManager() {
        this.meetings = new ArrayList<>();
    }

    public void createMeeting(String meetingLink, String topic, String time, String location) {
        Meeting newMeeting = new Meeting(meetingLink, topic, time, location);
        meetings.add(newMeeting);
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public static class Meeting implements Serializable {
        private String meetingLink;
        private String topic;
        private String time;
        private String location;
        private List<String> registeredUsers;


        public Meeting(String meetingLink, String topic, String time, String location)  {
            this.meetingLink = meetingLink;
            this.topic = topic;
            this.time = time;
            this.location = location;
            this.registeredUsers = new ArrayList<>();
        }

        public String getMeetingLink() {
            return meetingLink;
        }

        public String getTopic() {
            return topic;
        }

        public String getTime() {
            return time;
        }

        public String getLocation() {
            return location;
        }

        @Override
        public String toString() {
            return "Topic: " + topic + "\n" +
                    "Time: " + time + "\n" +
                    "Location: " + location + "\n" +
                    "Meeting Link: " + meetingLink;
        }

        public List<String> getRegisteredUsers() {
            return registeredUsers;
        }

        public void registerUser(String userName) {
            registeredUsers.add(userName);
        }

        public void setRegisteredUsers(List<String> registeredUsers) {
            this.registeredUsers = registeredUsers;
        }
    }
}
