package com.example.interviewguider; // Replace with your actual package name

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signupTextView;
    private ImageView passwordVisibility;

    private boolean passwordVisible = false; // To track password visibility

    FirebaseAuth firebaseAuth;

    // SharedPreferences key for storing the last login timestamp
    private static final String LAST_LOGIN_TIMESTAMP = "last_login_timestamp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signupTextView = findViewById(R.id.loginLinkTextView);
        passwordVisibility = findViewById(R.id.passwordVisibility); // Initialize the ImageView

        // Check if the user needs to log in again
        if (shouldPromptForLogin()) {
            // Show login UI
            showLoginUI();
        } else {
            // Skip login and go to DomainActivity
            startActivity(new Intent(LoginActivity.this, DomainActivity.class));
            finish();
        }

        // Set a click listener for the password visibility image
        passwordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle password visibility
                passwordVisible = !passwordVisible;

                // Update the password field's input type based on visibility
                if (passwordVisible) {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordVisibility.setImageResource(R.drawable.openeye); // Show open eye icon
                } else {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordVisibility.setImageResource(R.drawable.closeeye); // Show closed eye icon
                }

                // Move the cursor to the end of the text in the password field
                passwordEditText.setSelection(passwordEditText.getText().length());
            }
        });

        // Set a click listener for the signup text
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });

        // Initialize the "Forgot Password?" TextView
        TextView forgotPasswordTextView = findViewById(R.id.forgotPasswordTextView);

        // Set a click listener for the "Forgot Password?" TextView
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call a method to handle the "Forgot Password?" functionality
                resetPassword();
            }
        });
    }

    // Show your login UI elements
    private void showLoginUI() {
        emailEditText.setVisibility(View.VISIBLE);
        passwordEditText.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.VISIBLE);
        signupTextView.setVisibility(View.VISIBLE);
        passwordVisibility.setVisibility(View.VISIBLE);
        // ... (show other UI elements)

        // Set a click listener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user-entered email and password
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Validate login
                if (isValidLogin(email, password)) {
                    performLogin(email, password);
                } else {
                    // Invalid login, show an error message
                    Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Sample login validation function (replace with your actual logic)
    private boolean isValidLogin(String email, String password) {
        // For demonstration purposes, you can add your login validation logic here
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Check if the email is in a valid format
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        // For this example, we'll check if both fields are not empty
        return true;
    }

    // Method to handle "Forgot Password?" functionality
    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(this, "Enter your email to reset your password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Use Firebase Authentication to send a password reset email
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Password reset email sent. Check your inbox.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to send password reset email.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Perform the login
    private void performLogin(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Successful login, save the timestamp and navigate to the main activity
                            saveLoginTimestamp();
                            startActivity(new Intent(LoginActivity.this, DomainActivity.class));
                            finish();
                        } else {
                            // Login failed, show an error message
                            Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Check if more than 24 hours have passed since the last login
    private boolean shouldPromptForLogin() {
        long lastLoginTimestamp = getSavedLoginTimestamp();
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastLoginTimestamp;
        return elapsedTime >= (96 * 60 * 60 * 1000); // 96 hours in milliseconds
    }

    // Save the current timestamp to SharedPreferences
    private void saveLoginTimestamp() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(LAST_LOGIN_TIMESTAMP, System.currentTimeMillis());
        editor.apply();
    }

    // Retrieve the saved timestamp from SharedPreferences
    private long getSavedLoginTimestamp() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        return preferences.getLong(LAST_LOGIN_TIMESTAMP, 0);
    }

    public void openSignupActivity() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
