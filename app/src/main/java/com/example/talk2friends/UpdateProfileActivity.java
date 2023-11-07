package com.example.talk2friends;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class UpdateProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextDateOfBirth, editTextInterests;
    private CheckBox checkBoxNativeEnglishSpeaker;
    private Button buttonUpdateProfile;

    // Firebase
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();
        // Assuming we have a node for users in our Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        editTextName = findViewById(R.id.editTextName);editTextDateOfBirth = findViewById(R.id.editTextDateOfBirth);
        checkBoxNativeEnglishSpeaker = findViewById(R.id.checkBoxNativeEnglishSpeaker);
        editTextInterests = findViewById(R.id.editTextInterests);
        buttonUpdateProfile = findViewById(R.id.buttonUpdateProfile);

        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String dob = editTextDateOfBirth.getText().toString().trim();
                boolean isNativeSpeaker = checkBoxNativeEnglishSpeaker.isChecked();
                String interests = editTextInterests.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(dob) && !TextUtils.isEmpty(interests)) {
                    updateUserProfile(name, dob, isNativeSpeaker, interests);
                } else {
                    Toast.makeText(UpdateProfileActivity.this, "Please fill out all fields", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateUserProfile(String name, String dob, boolean isNativeSpeaker, String interests) {
        String userId = firebaseAuth.getCurrentUser().getUid();

        Map<String, Object> profileUpdates = new HashMap<>();
        profileUpdates.put("name", name);
        profileUpdates.put("dob", dob);
        profileUpdates.put("nativeEnglishSpeaker", isNativeSpeaker);
        profileUpdates.put("interests", interests);

        databaseReference.child(userId).updateChildren(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(UpdateProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}