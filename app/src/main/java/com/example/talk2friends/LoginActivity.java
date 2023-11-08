package com.example.talk2friends;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isValidCredentials(email, password)) {
                    // Implement your login logic here
                    // For example, you can add Firebase authentication here

                    // Example code for redirection to MainActivity
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish(); // Close the LoginActivity
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String email, String password) {
        // Add your validation logic here
        // For example, you can check if the email and password are not empty

        // Example validation check (replace with your logic)
        return !email.isEmpty() && !password.isEmpty();
    }
}
