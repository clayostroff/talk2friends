package com.example.talk2friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText, ageEditText;
    private CheckBox nativeEnglishSpeakerCheckbox, nativeSpanishSpeakerCheckbox;
    private CheckBox sportsCheckbox, moviesCheckbox, musicCheckbox, readingCheckbox, cookingCheckbox, travelCheckbox, artCheckbox, gamingCheckbox, fitnessCheckbox, photographyCheckbox, technologyCheckbox, fashionCheckbox;
    private Button registerButton;
    private FirebaseAuth firebaseAuth;
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        ageEditText = findViewById(R.id.ageEditText);
        nativeEnglishSpeakerCheckbox = findViewById(R.id.nativeEnglishSpeakerCheckbox);
        nativeSpanishSpeakerCheckbox = findViewById(R.id.nativeSpanishSpeakerCheckbox);
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
        registerButton = findViewById(R.id.registerButton);

        firebaseAuth = FirebaseAuth.getInstance();
        profile = new Profile();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String age = ageEditText.getText().toString();
                boolean isNativeEnglishSpeaker = nativeEnglishSpeakerCheckbox.isChecked();
                boolean isNativeSpanishSpeaker = nativeSpanishSpeakerCheckbox.isChecked();
                String interests = getSelectedInterests();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || age.isEmpty() || interests.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                saveToFirebase(name, email, password, age, isNativeEnglishSpeaker, isNativeSpanishSpeaker, interests);
                                Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private String getSelectedInterests() {
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

        // Remove the trailing comma and space if there are selected interests
        if (interests.length() > 0) {
            interests.setLength(interests.length() - 2);
        }

        return interests.toString();
    }

    private void saveToFirebase(String name, String email, String password, String age, boolean isNativeEnglishSpeaker, boolean isNativeSpanishSpeaker, String interests) {
        profile.setName(name);
        profile.setEmail(email);
        profile.setPassword(password);
        profile.setAge(age);
        profile.setNativeEnglishSpeaker(isNativeEnglishSpeaker);
        profile.setNativeSpanishSpeaker(isNativeSpanishSpeaker);
        profile.setInterests(interests);

        // Save the profile to the database
        // Your implementation here
    }
}
