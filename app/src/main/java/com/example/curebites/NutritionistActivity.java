package com.example.curebites;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NutritionistActivity extends AppCompatActivity {

    private TextView slot6;

    // Tracks which slot is currently selected
    private TextView selectedSlot = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritionist);

        setupBackButton();
        setupSlots();
        setupChatButton();
        setupBottomNav();
    }

    // ── Back Arrow ───────────────────────────────────────────
    private void setupBackButton() {
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }

    // ── Time Slot Selection ──────────────────────────────────
    private void setupSlots() {
        // All slot TextViews
        TextView slot1 = findViewById(R.id.slot1);
        TextView slot2 = findViewById(R.id.slot2);
        TextView slot3 = findViewById(R.id.slot3);
        TextView slot4 = findViewById(R.id.slot4);
        TextView slot5 = findViewById(R.id.slot5);
        slot6 = findViewById(R.id.slot6);

        TextView[] allSlots = {slot1, slot2, slot3, slot4, slot5, slot6};

        selectedSlot = slot2;

        for (TextView slot : allSlots) {
            slot.setOnClickListener(v -> {
                // Reset previously selected slot back to grey
                if (selectedSlot != null) {
                    selectedSlot.setBackgroundResource(R.drawable.gray_circle);
                    selectedSlot.setTextColor(
                            getResources().getColor(R.color.grey_text, null)
                    );
                }

                // Highlight the newly tapped slot
                selectedSlot = (TextView) v;
                selectedSlot.setBackgroundResource(R.drawable.green_circle);
                selectedSlot.setTextColor(
                        getResources().getColor(android.R.color.white, null)
                );

                Toast.makeText(
                        this,
                        "Slot selected: " + selectedSlot.getText(),
                        Toast.LENGTH_SHORT
                ).show();
            });
        }
    }

    // ── Chat Button ──────────────────────────────────────────
    private void setupChatButton() {
        Button btnChat = findViewById(R.id.btnChat);
        btnChat.setOnClickListener(v -> {
            // Replace ChatActivity.class with your actual chat screen
            // Intent intent = new Intent(this, ChatActivity.class);
            // startActivity(intent);
            Toast.makeText(
                    this,
                    "Opening chat with Dr. Reena Shah...",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }

    // ── Bottom Navigation Bar ────────────────────────────────
    private void setupBottomNav() {
        LinearLayout navHome     = findViewById(R.id.navHome);
        LinearLayout navPlans    = findViewById(R.id.navPlans);
        LinearLayout navProgress = findViewById(R.id.navProgress);
        LinearLayout navProfile  = findViewById(R.id.navProfile);

        navHome.setOnClickListener(v -> {
            // startActivity(new Intent(this, HomeActivity.class));
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        });

        navPlans.setOnClickListener(v -> {
            // startActivity(new Intent(this, PlansActivity.class));
            Toast.makeText(this, "Plans", Toast.LENGTH_SHORT).show();
        });

        navProgress.setOnClickListener(v -> {
            // startActivity(new Intent(this, MyProgressActivity.class));
            Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show();
        });

        navProfile.setOnClickListener(v -> {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        });
    }
}