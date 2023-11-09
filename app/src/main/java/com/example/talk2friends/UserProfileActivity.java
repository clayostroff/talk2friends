package com.example.talk2friends;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class UserProfileActivity extends AppCompatActivity {

    // Views
    private TextView textViewName, textViewAge, textViewInterests;
  //  private CheckBox checkBoxIsNativeEnglishSpeaker;
    private Button buttonEditProfile;

    // Firebase
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize Firebase Auth and Database Reference
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Initialize views
        textViewName = findViewById(R.id.textViewName);
        textViewAge = findViewById(R.id.textViewAge);
     //   checkBoxIsNativeEnglishSpeaker = findViewById(R.id.checkBoxIsNativeEnglishSpeaker);
        textViewInterests = findViewById(R.id.textViewInterests);
        buttonEditProfile = findViewById(R.id.buttonEditProfile);

        // Load user data
        loadUserProfile();

        // Set the click listener for the button
        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start the UpdateProfileActivity to edit the user profile
                Intent intent = new Intent(UserProfileActivity.this, UpdateProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadUserProfile() {
        if (firebaseAuth.getCurrentUser() != null) {
            String userId = firebaseAuth.getCurrentUser().getUid();
            databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // Assuming you have the following fields in your database for each user
                        String name = dataSnapshot.child("name").getValue(String.class);
                        int age = dataSnapshot.child("age").getValue(Integer.class);
                        Boolean isNativeEnglishSpeaker = dataSnapshot.child("nativeEnglishSpeaker").getValue(Boolean.class);
                        String interests = dataSnapshot.child("interests").getValue(String.class);

                        // Set the user profile data to TextViews
                        textViewName.setText("Name: " + name);
                        textViewAge.setText("Age: " + age);
                        textViewInterests.setText("Interests: " + (interests != null ? interests : "N/A"));
                    } else {
                        Toast.makeText(UserProfileActivity.this, "User data does not exist", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(UserProfileActivity.this, "Failed to load user data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(UserProfileActivity.this, "Current user is null", Toast.LENGTH_SHORT).show();
        }
    }
}
