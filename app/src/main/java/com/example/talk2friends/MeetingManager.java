package com.example.talk2friends;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MeetingManager
{
    private List<Meeting> meetings;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    public MeetingManager() {
        this.meetings = new ArrayList<>();
    }

    public void createMeeting(String meetingLink, String topic, String time, String location) {
        Meeting newMeeting = new Meeting(meetingLink, topic, time, location);
        meetings.add(newMeeting);
    }

    public void saveMeeting(String meetingLink, String topic, String time, String location) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("meetings").document(meetingLink);
        Map<String, Object> meeting = new HashMap<>();
        meeting.put("link", meetingLink);
        meeting.put("topic", topic);
        meeting.put("time", time);
        meeting.put("location", location);
        documentReference.set(meeting);
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
