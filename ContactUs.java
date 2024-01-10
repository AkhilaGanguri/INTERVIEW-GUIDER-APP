package com.example.interviewguider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactUs extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactus);

        // Find the TextViews for the email addresses
        TextView email1 = findViewById(R.id.email1);
        TextView email2 = findViewById(R.id.email2); // Make sure to match the IDs
        TextView email3 = findViewById(R.id.email3);
        TextView email4 = findViewById(R.id.email4);
        TextView email5 = findViewById(R.id.email5);

        // Set click listeners for each email address
        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("ganguriakhila@gmail.com");
            }
        });

        email2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("eegameghana88@gmail.com");
            }
        });

        email3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("sunithabalabadra@gmail.com");
            }
        });

        email4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("bhavanidarapu006@gmail.com");
            }
        });

        email5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail("allahbakshu78@gmail.com");
            }
        });
    }

    private void sendEmail(String emailAddress) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", emailAddress, null));
        startActivity(emailIntent);
    }
    public void goToHomePage(View view) {
        Intent intent = new Intent(this, DomainActivity.class);
        startActivity(intent);
    }
}
