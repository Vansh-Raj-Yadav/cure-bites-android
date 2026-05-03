package com.example.curebites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OrderPlacedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        setupTrackButton();
    }

    public void setContentView(int activityOrderPlaced) {
    }

    // ── Track My Order Button ────────────────────────────────
    private void setupTrackButton() {
        @SuppressLint("WrongViewCast") Button trackBtn = findViewById(R.id.backBtn);
        trackBtn.setOnClickListener(v -> {
            Intent intent = new Intent(OrderPlacedActivity.this, OrderPlacedActivity.class);
            startActivity(intent);
        });
    }
}
