package com.example.interviewguider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DomainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to use the new layout resource
        setContentView(R.layout.domain);

        // Your code for handling button clicks and other functionality remains the same
        Button webDevelopmentButton = findViewById(R.id.webDevelopmentButton);
        Button cyberSecurityButton = findViewById(R.id.cyberSecurityButton);
        Button dataScienceButton = findViewById(R.id.dataScienceButton);
        Button appDevelopmentButton = findViewById(R.id.appDevelopmentButton);


        webDevelopmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DomainActivity.this, WebDev.class));
            }
        });

        // Handle other button clicks...
        cyberSecurityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DomainActivity.this, CyberActivity.class));
            }
        });
        dataScienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DomainActivity.this, DsActivity.class));
            }
        });
        appDevelopmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DomainActivity.this, AppdevActivity.class));
            }
        });
    }
    public void goToContactUs(View view) {
        Intent intent = new Intent(this, ContactUs.class);
        startActivity(intent);
    }
    public void goToFeedback(View view) {
        Intent intent = new Intent(this, Userfeedback.class);
        startActivity(intent);
    }
    public void goToHomePage(View view) {
        Intent intent = new Intent(this, DomainActivity.class);
        startActivity(intent);
    }
    public void goToTips(View view) {
        Intent intent = new Intent(this, Tips.class);
        startActivity(intent);
    }
}