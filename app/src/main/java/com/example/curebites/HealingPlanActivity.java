package com.example.curebites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * HealingPlanActivity
 *
 * This screen shows 4 meal plan options:
 *   1. Trial Pack      – 1 meal/day for 3 days
 *   2. Daily Meal Plan – all meals for a full day
 *   3. Weekly Plan     – all meals for 7 days
 *   4. Monthly Plan    – all meals for 30 days
 *
 * Each plan has a "Get Started" button.
 * The bottom nav bar switches between Home / Plans / Progress / Profile.
 */
public class HealingPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healing_plan);

        // ── Back arrow ──────────────────────────────────────────────
        // Goes back to the previous screen
        findViewById(R.id.ivBack).setOnClickListener(v -> finish());

        // ── Cart icon ───────────────────────────────────────────────
        // TODO: navigate to CartActivity when built
        findViewById(R.id.ivCart).setOnClickListener(v ->
                Toast.makeText(this, "Cart coming soon!", Toast.LENGTH_SHORT).show());

        // ── Plan buttons ────────────────────────────────────────────
        // Each button calls the same helper with a different plan name.
        // Later you can navigate to an order/detail screen instead.

        Button btnTrial = findViewById(R.id.btnTrial);
        btnTrial.setOnClickListener(v -> onPlanSelected("Trial Pack"));

        Button btnDaily = findViewById(R.id.btnDaily);
        btnDaily.setOnClickListener(v -> onPlanSelected("Daily Meal Plan"));

        Button btnWeekly = findViewById(R.id.btnWeekly);
        btnWeekly.setOnClickListener(v -> onPlanSelected("Weekly Plan"));

        Button btnMonthly = findViewById(R.id.btnMonthly);
        btnMonthly.setOnClickListener(v -> onPlanSelected("Monthly Plan"));

        // ── Bottom navigation bar ───────────────────────────────────
        setupBottomNav();
    }

    /**
     * onPlanSelected(planName)
     *
     * Called when the user taps "Get Started" on any plan card.
     * Currently shows a toast — replace with navigation to the next screen.
     *
     * @param planName  The name of the selected plan (e.g. "Weekly Plan")
     */
    private void onPlanSelected(String planName) {
        Intent intent = new Intent(this, MealPlanActivity.class);
        intent.putExtra("PLAN_NAME", planName);
        startActivity(intent);
    }

    /**
     * setupBottomNav()
     *
     * Wires up the 4 bottom navigation tabs.
     * "Plans" is the active tab on this screen (already highlighted green in XML).
     */
    private void setupBottomNav() {

        // Home tab → go to HomeActivity
        LinearLayout tabHome = findViewById(R.id.tabHome);
        tabHome.setOnClickListener(v -> {
            // TODO: replace with your actual HomeActivity
            // startActivity(new Intent(this, HomeActivity.class));
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        });

        // Plans tab → already here, do nothing (or refresh)
        LinearLayout tabPlans = findViewById(R.id.tabPlans);
        tabPlans.setOnClickListener(v -> {
            // Already on this screen — no action needed
        });

        // Progress tab → go to ProgressActivity
        LinearLayout tabProgress = findViewById(R.id.tabProgress);
        tabProgress.setOnClickListener(v -> {
            // TODO: replace with your actual ProgressActivity
            Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show();
        });

        // Profile tab → go to ProfileActivity
        LinearLayout tabProfile = findViewById(R.id.tabProfile);
        tabProfile.setOnClickListener(v -> {
            // TODO: replace with your actual ProfileActivity
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        });
    }
}
