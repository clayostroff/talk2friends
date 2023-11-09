package com.example.talk2friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddMeetingActivity extends AppCompatActivity {

    private EditText linkEditText, topicEditText, timeEditText, locationEditText;

    FirebaseFirestore ff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        linkEditText = findViewById(R.id.linkEditText);
        topicEditText = findViewById(R.id.topicEditText);
        timeEditText = findViewById(R.id.timeEditText);
        locationEditText = findViewById(R.id.locationEditText);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String meetingLink = linkEditText.getText().toString();
                String topic = topicEditText.getText().toString();
                String time = timeEditText.getText().toString();
                String location = locationEditText.getText().toString();

                ff = FirebaseFirestore.getInstance();
                DocumentReference documentReference = ff.collection("meetings").document();
                Map<String, Object> meeting = new HashMap<>();
                meeting.put("link", meetingLink);
                meeting.put("topic", topic);
                meeting.put("time", time);
                meeting.put("location", location);
                documentReference.set(meeting);

                Intent intent = new Intent();
                intent.putExtra("meetingLink", meetingLink);
                intent.putExtra("topic", topic);
                intent.putExtra("time", time);
                intent.putExtra("location", location);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
