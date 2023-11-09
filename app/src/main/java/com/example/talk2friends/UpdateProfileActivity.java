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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//add a button for invitation email
//invite page 
//click invite


public class UpdateProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge;
    private Button buttonUpdateProfile;

    private CheckBox sportsCheckbox, moviesCheckbox, musicCheckbox, readingCheckbox, cookingCheckbox, travelCheckbox, artCheckbox, gamingCheckbox, fitnessCheckbox, photographyCheckbox, technologyCheckbox, fashionCheckbox;


    // Firebase
    private FirebaseFirestore ff;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
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
            public void onClick(View view)
            {
                ff = FirebaseFirestore.getInstance();
                String name = editTextName.getText().toString().trim();
                String age = editTextAge.getText().toString().trim();

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

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age)) {
                    String userID = firebaseAuth.getCurrentUser().getUid();
                    ff.collection("profiles").document(userID).update("name", name, "age", age, "interests", interests.toString());
                    Toast.makeText(UpdateProfileActivity.this, "Profile updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(UpdateProfileActivity.this, "Please fill out all fields", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void updateUserProfile(String name, Integer age, String interests) {

    }
}