package com.example.talk2friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class UserOptionsActivity extends AppCompatActivity {

    private Button userProfileButton, meetingManagerButton, inviteFriendButton, friendsButton, friendRecommenedButton, logoutButton;

    Profile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);

        Intent intent = getIntent();
        userProfile = (Profile) intent.getSerializableExtra("userProfile");


        userProfileButton = findViewById(R.id.userProfileButton);
        meetingManagerButton = findViewById(R.id.meetingManagerButton);
        inviteFriendButton = findViewById(R.id.inviteFriendButton);
        friendsButton = findViewById(R.id.friendsButton);
        friendRecommenedButton = findViewById(R.id.recommendFriendsButton);
        logoutButton = findViewById(R.id.logoutButton);

        userProfileButton.setOnClickListener(v -> openUserProfileActivity());
        meetingManagerButton.setOnClickListener(v -> openMeetingManagerActivity());
        inviteFriendButton.setOnClickListener(v -> openFriendLinkActivity());
        friendsButton.setOnClickListener(v -> openFriendsActivity());
        friendRecommenedButton.setOnClickListener(v -> openRecommenedFriendsActivity());
        logoutButton.setOnClickListener(v -> openLogoutActivity());
    }

    private void openUserProfileActivity() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }

    private void openMeetingManagerActivity() {
        Intent intent = new Intent(this, MeetingManagerActivity.class);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }

    private void openFriendLinkActivity() {
        Intent intent = new Intent(this, InviteFriendActivity.class);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }

    private void openFriendsActivity() {
        Intent intent = new Intent(this, FriendsActivity.class);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }

    private void openRecommenedFriendsActivity() {
        Intent intent = new Intent(this, RecommendFriendsActivity.class);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }

    private void openLogoutActivity() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
