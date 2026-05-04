package com.example.curebites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Firebase Imports
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TellUsActivity extends AppCompatActivity {

    // Firebase instances
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    // UI Elements
    private TextView tvAgeValue;
    private TextView tvWeightValue;
    private TextView tvHeightValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell_us);

        // 1. Initialize Firebase
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        // 2. Bind TextViews for value display
        tvAgeValue    = findViewById(R.id.tvAgeValue);
        tvWeightValue = findViewById(R.id.tvWeightValue);
        tvHeightValue = findViewById(R.id.tvHeightValue);

        // 3. Age SeekBar (Range: 1 to 100)
        SeekBar seekAge = findViewById(R.id.seekAge);
        seekAge.setMax(100);
        seekAge.setProgress(28); // Default
        seekAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvAgeValue.setText(String.valueOf(progress));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 4. Weight SeekBar (Range: 1 to 200 kg)
        SeekBar seekWeight = findViewById(R.id.seekWeight);
        seekWeight.setMax(200);
        seekWeight.setProgress(72); // Default
        seekWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvWeightValue.setText(String.valueOf(progress));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 5. Height SeekBar (Range: 50 to 250 cm)
        SeekBar seekHeight = findViewById(R.id.seekHeight);
        seekHeight.setMax(250);
        seekHeight.setProgress(172); // Default
        seekHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvHeightValue.setText(String.valueOf(progress));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // 6. Continue Button with Firestore Logic
        Button btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(v -> {
            // Check if user is logged in
            if (mAuth.getCurrentUser() != null) {
                String userId = mAuth.getCurrentUser().getUid();

                // Map data to save
                Map<String, Object> profile = new HashMap<>();
                profile.put("age", seekAge.getProgress());
                profile.put("weight", seekWeight.getProgress());
                profile.put("height", seekHeight.getProgress());

                // Save to "users" collection in Firestore
                db.collection("users").document(userId)
                        .set(profile)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(this, "Profile Saved!", Toast.LENGTH_SHORT).show();
                            // Move to HomeActivity after successful save
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Save Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
