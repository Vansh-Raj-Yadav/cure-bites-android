package com.example.curebites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MealPlanTuesdayActivity
 *
 * This is the Tuesday version of the meal plan screen.
 * It shows:
 *   - Tuesday tab highlighted green
 *   - Tuesday meals: Vegetable Upma, Mexican Bowl, Lentil-Based Salad
 *
 * Tapping Mon tab goes back to MealPlanActivity (Monday screen).
 * Tapping Wed/Thu/Fri shows a "Coming soon" toast for now.
 */
public class MealPlanTuesdayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan_tuesday);

        // ── Back arrow — goes to previous screen ───────────────────
        findViewById(R.id.ivBack).setOnClickListener(v -> finish());

        // ── Cart icon ──────────────────────────────────────────────
        findViewById(R.id.ivCart).setOnClickListener(v ->
                Toast.makeText(this, "Cart coming soon!", Toast.LENGTH_SHORT).show());

        // ── Day tabs ───────────────────────────────────────────────
        setupDayTabs();

        // ── Subscribe button ───────────────────────────────────────
        Button btnSubscribe = findViewById(R.id.btnSubscribe);
        btnSubscribe.setOnClickListener(v ->
                Toast.makeText(this,
                        "Subscribed to Monthly Meal Plan!",
                        Toast.LENGTH_SHORT).show());

        // ── Bottom navigation ──────────────────────────────────────
        setupBottomNav();
    }

    /**
     * setupDayTabs()
     *
     * Handles tapping on the day tabs.
     * Mon → goes back to Monday screen (MealPlanActivity)
     * Tue → already here, do nothing
     * Wed/Thu/Fri → shows toast (your friend can build those)
     */
    private void setupDayTabs() {

        // Tapping Mon goes BACK to the Monday meal plan screen
        LinearLayout tabMon = findViewById(R.id.tabMon);
        tabMon.setOnClickListener(v -> {
            // Go back to Monday screen
            Intent intent = new Intent(this, MealPlanActivity.class);
            startActivity(intent);
            finish(); // close Tuesday screen so back button works correctly
        });

        // Already on Tuesday — no action needed
        LinearLayout tabTue = findViewById(R.id.tabTue);
        tabTue.setOnClickListener(v -> { });

        // Wed, Thu, Fri — show toast (add more activities later)
        LinearLayout tabWed = findViewById(R.id.tabWed);
        tabWed.setOnClickListener(v ->
                Toast.makeText(this, "Wednesday coming soon!", Toast.LENGTH_SHORT).show());

        LinearLayout tabThu = findViewById(R.id.tabThu);
        tabThu.setOnClickListener(v ->
                Toast.makeText(this, "Thursday coming soon!", Toast.LENGTH_SHORT).show());

        LinearLayout tabFri = findViewById(R.id.tabFri);
        tabFri.setOnClickListener(v ->
                Toast.makeText(this, "Friday coming soon!", Toast.LENGTH_SHORT).show());
    }

    /**
     * setupBottomNav()
     * Wires the 4 bottom tabs.
     */
    private void setupBottomNav() {

        findViewById(R.id.tabHome).setOnClickListener(v ->
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show());

        findViewById(R.id.tabPlans).setOnClickListener(v -> { });

        findViewById(R.id.tabProgress).setOnClickListener(v ->
                Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show());

        findViewById(R.id.tabProfile).setOnClickListener(v ->
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show());
    }
}