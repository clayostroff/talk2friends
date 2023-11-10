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
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import androidx.annotation.NonNull;

public class UserProfileActivity extends AppCompatActivity {

    // Views
    private TextView textViewName, textViewAge, textViewLanguage, textViewInterests;
  //  private CheckBox checkBoxIsNativeEnglishSpeaker;
    private Button buttonEditProfile;

    // Firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize Firebase Auth and Database Reference
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize views
        textViewName = findViewById(R.id.textViewName);
        textViewAge = findViewById(R.id.textViewAge);
        textViewLanguage = findViewById(R.id.textViewLanguage);
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
            String userID = firebaseAuth.getCurrentUser().getUid();
            DocumentReference docRef = FirebaseFirestore.getInstance().collection("profiles").document(userID);

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Use getString to retrieve a String field
                            String name = document.getString("name");
                            String age = document.getString("age");
                            String language;
                            if(document.getBoolean("native")){
                                language = "English";
                            } else language = "Spanish";
                            String interests = document.getString("interests");

                            textViewName.setText("Name: " + name);
                            textViewAge.setText("Age: " + age);
                            textViewLanguage.setText("Native Language: " + language);
                            textViewInterests.setText("Interests: " + (interests != null ? interests : "N/A"));
                        } else {
                            Toast.makeText(UserProfileActivity.this, "User data does not exist", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UserProfileActivity.this, "User data does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(UserProfileActivity.this, "Current user is null", Toast.LENGTH_SHORT).show();
        }
    }
}
