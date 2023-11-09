package com.example.talk2friends;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, passwordEditText, ageEditText;
    private CheckBox nativeEnglishSpeakerCheckbox, nativeSpanishSpeakerCheckbox;
    private CheckBox sportsCheckbox, moviesCheckbox, musicCheckbox, readingCheckbox, cookingCheckbox, travelCheckbox, artCheckbox, gamingCheckbox, fitnessCheckbox, photographyCheckbox, technologyCheckbox, fashionCheckbox;
    private Button registerButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    String userID;
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
        firebaseFirestore = FirebaseFirestore.getInstance();
        profile = new Profile();

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), UserOptionsActivity.class));
        }

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String age = ageEditText.getText().toString();
                boolean isNativeEnglishSpeaker = nativeEnglishSpeakerCheckbox.isChecked();
                boolean isNativeSpanishSpeaker = nativeSpanishSpeakerCheckbox.isChecked();

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

                // Toast.makeText(RegistrationActivity.this, String.valueOf((email.substring(email.length() - 8)).equals("@usc.edu")), Toast.LENGTH_SHORT).show();

                if (name.isEmpty() || email.isEmpty() || password.length() < 6 || age.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields. Password must be at least 6 characters long.", Toast.LENGTH_SHORT).show();
                } else if (!((email.substring(email.length() - 8)).equals("@usc.edu"))) {
                    Toast.makeText(RegistrationActivity.this, "Only USC students allowed", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseAuth.getCurrentUser().sendEmailVerification();
                                userID = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = firebaseFirestore.collection("profiles").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", name);
                                user.put("email", email);
                                user.put("age", age);
                                user.put("native", isNativeEnglishSpeaker);
                                user.put("non-native", isNativeSpanishSpeaker);
                                user.put("interests", interests.toString());
                                documentReference.set(user);
                                Toast.makeText(RegistrationActivity.this, "Registration successful. Please verify your email to sign in.", Toast.LENGTH_SHORT).show();
                                firebaseAuth.getInstance().signOut();
                                finish();
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Error 23", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
