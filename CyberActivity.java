package com.example.interviewguider;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CyberActivity extends AppCompatActivity {

    private Button aptitudeTestButton;
    private Button technicalRoundButton;
    private Button hrInterviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cyber); // Replace 'your_layout' with the actual layout file name

        // Initialize buttons
        aptitudeTestButton = findViewById(R.id.webDevelopmentButton);
        technicalRoundButton = findViewById(R.id.cyberSecurityButton);
        hrInterviewButton = findViewById(R.id.dataScienceButton);

        // Set click listeners for the buttons

        aptitudeTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CyberActivity.this, RulesActivity.class);
                intent.putExtra("backgroundColor", getResources().getColor(R.color.grey_background));
                intent.putExtra("domainIdentifier", "Domain2");
                String selectedDomain = "Domain2";
                startActivity(intent);
            }
        });

        technicalRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle technical round button click
                Intent intent = new Intent(CyberActivity.this, Cybertech.class);
                startActivity(intent);
            }
        });

        hrInterviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle HR interview button click
                Intent intent = new Intent(CyberActivity.this, RulesHr.class);
                startActivity(intent);
            }
        });
    }
    public void goToHomePage(View view) {
        // Define the code to navigate to the home page here
        // For example, you can use an Intent to start the home page activity
        Intent intent = new Intent(this, DomainActivity.class); // Replace with your home page activity
        startActivity(intent);
    }
    private void showToast(String message) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    public void goToContactUs(View view) {
        Intent intent = new Intent(this, ContactUs.class);
        startActivity(intent);
    }
    public void goToFeedback(View view) {
        Intent intent = new Intent(this, Userfeedback.class);
        startActivity(intent);
    }
    public void goToTips(View view) {
        Intent intent = new Intent(this, Tips.class);
        startActivity(intent);
    }
}