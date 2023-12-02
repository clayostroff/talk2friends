package com.example.talk2friends;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MeetingDetailsActivity extends AppCompatActivity {
    private MeetingManager.Meeting meeting;
    private TextView registeredUsersTextView;
    private SharedPreferences sharedPreferences;

    private Profile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        // Retrieve the selected meeting from the intent
        meeting = (MeetingManager.Meeting) getIntent().getSerializableExtra("meeting");
        userProfile = (Profile) getIntent().getSerializableExtra("userProfile");

        // Load registered users
        if (meeting != null) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("registered_users_" + meeting.getTopic(), null);
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            List<String> registeredUsers = gson.fromJson(json, type);
            if (registeredUsers != null) {
                meeting.setRegisteredUsers(registeredUsers);
            }
        }

        Button backToMeetingsButton = findViewById(R.id.backToMeetingsButton);
        backToMeetingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView topicTextView = findViewById(R.id.topicTextView);
        TextView timeTextView = findViewById(R.id.timeTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView linkTextView = findViewById(R.id.linkTextView);

        // Retrieve the selected meeting from the intent
        meeting = (MeetingManager.Meeting) getIntent().getSerializableExtra("meeting");
        registeredUsersTextView = findViewById(R.id.registeredUsersTextView);

        // Set the meeting details to the corresponding TextViews
        if (meeting != null) {
            topicTextView.setText(meeting.getTopic());
            timeTextView.setText(meeting.getTime());
            locationTextView.setText(meeting.getLocation());
            linkTextView.setText(meeting.getMeetingLink());
            displayRegisteredUsers();
        }

        // Find the unregister button
        Button unregisterButton = findViewById(R.id.unregisterButton);
        unregisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterForMeeting(v);
            }
        });
    }

    public void registerForMeeting(View view) {
        if (meeting != null) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            if (firebaseAuth.getCurrentUser() != null) {
                String userID = firebaseAuth.getCurrentUser().getUid();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("profiles").document(userID);

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String name = document.getString("name");
                                if (!meeting.getRegisteredUsers().contains(name)) {
                                    meeting.registerUser(name); // Register the user for the meeting with the retrieved name

                                    // Save registered users
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(meeting.getRegisteredUsers());
                                    editor.putString("registered_users_" + meeting.getTopic(), json);
                                    editor.apply();
                                    displayRegisteredUsers();

                                    Toast.makeText(MeetingDetailsActivity.this, "You have successfully registered for the meeting!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MeetingDetailsActivity.this, "You have already registered for this meeting.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MeetingDetailsActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MeetingDetailsActivity.this, "Task not successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    public void unregisterForMeeting(View view) {
        if (meeting != null) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            if (firebaseAuth.getCurrentUser() != null) {
                String userID = firebaseAuth.getCurrentUser().getUid();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection("profiles").document(userID);

                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String name = document.getString("name");
                                if (meeting.getRegisteredUsers().contains(name)) {
                                    meeting.unregisterUser(name); // Unregister the user from the meeting with the retrieved name

                                    // Save registered users
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(meeting.getRegisteredUsers());
                                    editor.putString("registered_users_" + meeting.getTopic(), json);
                                    editor.apply();
                                    displayRegisteredUsers();

                                    Toast.makeText(MeetingDetailsActivity.this, "You have successfully unregistered from the meeting.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MeetingDetailsActivity.this, "You are not registered for this meeting.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MeetingDetailsActivity.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MeetingDetailsActivity.this, "Task not successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    public void backToMeetings(View view) {
        Intent intent = new Intent(this, MeetingManagerActivity.class);
        startActivity(intent);
    }

    private void displayRegisteredUsers() {
        List<String> registeredUsers = meeting.getRegisteredUsers();
        if (!registeredUsers.isEmpty()) {
            StringBuilder usersBuilder = new StringBuilder();
            for (String user : registeredUsers) {
                usersBuilder.append(user).append("\n");
            }
            registeredUsersTextView.setText(usersBuilder.toString());
        } else {
            registeredUsersTextView.setText("No users registered for this meeting yet.");
        }
    }
}
