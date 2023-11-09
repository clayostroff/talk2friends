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

    private EditText editTextName, editTextAge, editTextInterests;
   // private CheckBox checkBoxNativeEnglishSpeaker;
    private Button buttonUpdateProfile;

    private CheckBox sportsCheckbox, moviesCheckbox, musicCheckbox, readingCheckbox, cookingCheckbox, travelCheckbox, artCheckbox, gamingCheckbox, fitnessCheckbox, photographyCheckbox, technologyCheckbox, fashionCheckbox;


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

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
       // checkBoxNativeEnglishSpeaker = findViewById(R.id.checkBoxNativeEnglishSpeaker);
        //checkbox = findViewById(R.id.editTextInterests);
        sportsCheckbox = findViewById(R.id.sportsCheckbox);
        moviesCheckbox = findViewById(R.id.moviesCheckbox);
        musicCheckbox = findViewById(R.id.musicCheckbox);
        readingCheckbox = findViewById(R.id.readingCheckbox);
        cookingCheckbox = findViewById(R.id.cookingCheckbox);
        travelCheckbox = findViewById(R.id.travelCheckbox);
        artCheckbox = findViewById(R.id.artCheckbox);
        gamingCheckbox = findViewById(R.id.gamingCheckbox);
        fitnessCheckbox = findViewById(R.id.fitnessCheckbox);
        photographyCheckbox = findViewById(R.id.photographyCheckbox);
        technologyCheckbox = findViewById(R.id.technologyCheckbox);
        fashionCheckbox = findViewById(R.id.fashionCheckbox);



        buttonUpdateProfile = findViewById(R.id.buttonUpdateProfile);

        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String ageString = editTextAge.getText().toString().trim();
                //boolean isNativeSpeaker = checkBoxNativeEnglishSpeaker.isChecked();
            //    String interests = editTextInterests.getText().toString().trim();
                StringBuilder interests = new StringBuilder();
                if (sportsCheckbox.isChecked()) {
                    interests.append("Sports, ");
                }
                if (moviesCheckbox.isChecked()) {
                    interests.append("Movies, ");
                }
                if (musicCheckbox.isChecked()) {
                    interests.append("Music, ");
                }
                if (readingCheckbox.isChecked()) {
                    interests.append("Reading, ");
                }
                if (cookingCheckbox.isChecked()) {
                    interests.append("Cooking, ");
                }
                if (travelCheckbox.isChecked()) {
                    interests.append("Travel, ");
                }
                if (artCheckbox.isChecked()) {
                    interests.append("Art, ");
                }
                if (gamingCheckbox.isChecked()) {
                    interests.append("Gaming, ");
                }
                if (fitnessCheckbox.isChecked()) {
                    interests.append("Fitness, ");
                }
                if (photographyCheckbox.isChecked()) {
                    interests.append("Photography, ");
                }
                if (technologyCheckbox.isChecked()) {
                    interests.append("Technology, ");
                }
                if (fashionCheckbox.isChecked()) {
                    interests.append("Fashion, ");
                }
                if (interests.length() > 0) {
                    interests.setLength(interests.length() - 2);
                }

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(ageString)) {
                    // Initialize age with a default value
                    int age = 0;
                    // Try to parse the age string to an integer
                    try {
                        age = Integer.parseInt(ageString);
                    } catch (NumberFormatException e) {
                        // If the string does not contain a parsable integer, show a toast message
                        Toast.makeText(UpdateProfileActivity.this, "Please enter a valid age", Toast.LENGTH_LONG).show();
                        return; // Stop further execution in this case
                    }

                    // If the age is parsed successfully, update the user profile
                    updateUserProfile(name, age, interests.toString());
                } else {
                    Toast.makeText(UpdateProfileActivity.this, "Please fill out all fields", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void updateUserProfile(String name, Integer age, String interests) {
        String userId = firebaseAuth.getCurrentUser().getUid();

        Map<String, Object> profileUpdates = new HashMap<>();
        profileUpdates.put("name", name);
        profileUpdates.put("age", age);
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