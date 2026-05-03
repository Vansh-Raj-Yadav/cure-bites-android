package com.example.curebites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

class Order2 extends AppCompatActivity {

    // Delivery partner details
    private static final String PARTNER_PHONE = "+919876543210"; // Replace with real number
    private static final String PARTNER_EMAIL = "ramkumar@delivery.com"; // Replace with real email

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order2);

        setupBackButton();
        setupCallButton();
        setupMessageButton();
    }

    // ── Back Arrow ──────────────────────────────────────────
    private void setupBackButton() {
        ImageView backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish());
    }

    // ── Call Delivery Partner ────────────────────────────────
    private void setupCallButton() {
        ImageView callBtn = findViewById(R.id.backBtn);
        callBtn.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + PARTNER_PHONE));
            startActivity(callIntent);
        });
    }

    // ── Message Delivery Partner ─────────────────────────────
    private void setupMessageButton() {
        @SuppressLint("WrongViewCast") ImageView msgBtn = findViewById(R.id.main);
        msgBtn.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + PARTNER_EMAIL));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Query");
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}