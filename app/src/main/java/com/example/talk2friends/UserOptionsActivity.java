package com.example.talk2friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class UserOptionsActivity extends AppCompatActivity {

    private Button userProfileButton, meetingManagerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);

        userProfileButton = findViewById(R.id.userProfileButton);
        meetingManagerButton = findViewById(R.id.meetingManagerButton);

        userProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserProfileActivity();
            }
        });

        meetingManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMeetingManagerActivity();
            }
        });
    }

    private void openUserProfileActivity() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    private void openMeetingManagerActivity() {
        Intent intent = new Intent(this, MeetingManagerActivity.class);
        startActivity(intent);
    }
}
