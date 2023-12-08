package com.example.talk2friends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AddFriendsOption extends AppCompatActivity {


    private Button inviteFriendButton, friendRecommenedButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends_option);

        inviteFriendButton = findViewById(R.id.inviteFriendButton);
        friendRecommenedButton = findViewById(R.id.recommendFriendsButton);

        inviteFriendButton.setOnClickListener(v -> openFriendLinkActivity());
        friendRecommenedButton.setOnClickListener(v -> openRecommenedFriendsActivity());
    }

    private void openFriendLinkActivity() {
        Intent intent = new Intent(this, InviteFriendActivity.class);
        startActivity(intent);
    }

    private void openRecommenedFriendsActivity() {
        Intent intent = new Intent(this, RecommendFriendsActivity.class);
        startActivity(intent);
    }
}