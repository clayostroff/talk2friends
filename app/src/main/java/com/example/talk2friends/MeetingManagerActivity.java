package com.example.talk2friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MeetingManagerActivity extends AppCompatActivity {

    private ListView meetingsListView;
    private MeetingManager meetingManager;
    private ArrayAdapter<MeetingManager.Meeting> adapter;
    private static final int REQUEST_CODE_ADD_MEETING = 100;

    Profile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_manager);

        Intent intent = getIntent();
        userProfile = (Profile) intent.getSerializableExtra("userProfile");

        meetingsListView = findViewById(R.id.meetingsListView);
        meetingManager = new MeetingManager();

        // Add sample meetings
        meetingManager.createMeeting("https://zoom.us/meeting1", "English Practice", "5:00 PM", "Online");
        meetingManager.createMeeting("https://zoom.us/meeting2", "Language Exchange", "6:00 PM", "On Campus");

        List<MeetingManager.Meeting> meetings = meetingManager.getMeetings();

        adapter = new ArrayAdapter<MeetingManager.Meeting>(this, android.R.layout.simple_list_item_1, meetings) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);
                textView.setPadding(0, 20, 0, 20); // Adjust the padding as needed
                return view;
            }
        };
        meetingsListView.setAdapter(adapter);

        meetingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MeetingManager.Meeting selectedMeeting = meetings.get(position);
                openMeetingDetailsActivity(selectedMeeting);
            }
        });

        findViewById(R.id.backToMainButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.addMeetingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeetingManagerActivity.this, AddMeetingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_MEETING);
            }
        });
    }

    private void openMeetingDetailsActivity(MeetingManager.Meeting selectedMeeting) {
        Intent intent = new Intent(this, MeetingDetailsActivity.class);
        intent.putExtra("meeting", selectedMeeting);
        intent.putExtra("userProfile", userProfile);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_MEETING && resultCode == RESULT_OK) {
            String meetingLink = data.getStringExtra("meetingLink");
            String topic = data.getStringExtra("topic");
            String time = data.getStringExtra("time");
            String location = data.getStringExtra("location");

            meetingManager.createMeeting(meetingLink, topic, time, location);

            adapter.notifyDataSetChanged();
        }
    }
}
