package com.example.curebites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MealPlanActivity
 *
 * Shows Monday's meal plan by default.
 * Tapping Tue tab navigates to MealPlanTuesdayActivity.
 * Tapping Wed/Thu/Fri shows "coming soon" toast.
 */
public class MealPlanActivity extends AppCompatActivity {

    // ── Day tab references ─────────────────────────────────────────
    private LinearLayout[] dayTabs;

    // ── Breakfast TextViews ────────────────────────────────────────
    private TextView tvBreakfastTime;
    private TextView tvBreakfast1Emoji;
    private TextView tvBreakfast1Name;
    private TextView tvBreakfast1Cal;
    private TextView tvBreakfast2Emoji;
    private TextView tvBreakfast2Name;
    private TextView tvBreakfast2Cal;

    // ── Lunch TextViews ────────────────────────────────────────────
    private TextView tvLunchTime;
    private TextView tvLunchEmoji;
    private TextView tvLunchName;
    private TextView tvLunchCal;

    // ── Dinner TextViews ───────────────────────────────────────────
    private TextView tvDinnerTime;
    private TextView tvDinnerEmoji;
    private TextView tvDinnerName;
    private TextView tvDinnerCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_plan);

        // ── Cart icon ──────────────────────────────────────────────
        findViewById(R.id.ivCart).setOnClickListener(v ->
                Toast.makeText(this, "Cart coming soon!", Toast.LENGTH_SHORT).show());

        // ── Bind all meal TextViews to their XML IDs ───────────────
        bindMealViews();

        // ── Set up day tabs ────────────────────────────────────────
        setupDayTabs();

        // ── Highlight Monday as active by default ──────────────────
        setActiveDay(0);

        // ── Load Monday meals into the cards ──────────────────────
        loadMondayMeals();

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
     * bindMealViews()
     *
     * Connects all meal card TextViews in the XML to Java variables.
     * Must be called before loadMondayMeals().
     */
    private void bindMealViews() {

        // Breakfast
        tvBreakfastTime   = findViewById(R.id.tvBreakfastTime);
        tvBreakfast1Emoji = findViewById(R.id.tvBreakfast1Emoji);
        tvBreakfast1Name  = findViewById(R.id.tvBreakfast1Name);
        tvBreakfast1Cal   = findViewById(R.id.tvBreakfast1Cal);
        tvBreakfast2Emoji = findViewById(R.id.tvBreakfast2Emoji);
        tvBreakfast2Name  = findViewById(R.id.tvBreakfast2Name);
        tvBreakfast2Cal   = findViewById(R.id.tvBreakfast2Cal);

        // Lunch
        tvLunchTime  = findViewById(R.id.tvLunchTime);
        tvLunchEmoji = findViewById(R.id.tvLunchEmoji);
        tvLunchName  = findViewById(R.id.tvLunchName);
        tvLunchCal   = findViewById(R.id.tvLunchCal);

        // Dinner
        tvDinnerTime  = findViewById(R.id.tvDinnerTime);
        tvDinnerEmoji = findViewById(R.id.tvDinnerEmoji);
        tvDinnerName  = findViewById(R.id.tvDinnerName);
        tvDinnerCal   = findViewById(R.id.tvDinnerCal);
    }

    /**
     * loadMondayMeals()
     *
     * Sets Monday's meal data into all the meal card TextViews.
     * Breakfast: Oats with berries + Almond Milk
     * Lunch:     Quinoa Bowl
     * Dinner:    Moong Dal and Brown Rice
     */
    private void loadMondayMeals() {

        // Breakfast
        tvBreakfastTime.setText("7:00 - 8:30 am");
        tvBreakfast1Emoji.setText("🫐");
        tvBreakfast1Name.setText("Oats with berries");
        tvBreakfast1Cal.setText("320 cal");
        tvBreakfast2Emoji.setText("🥛");
        tvBreakfast2Name.setText("Almond Milk");
        tvBreakfast2Cal.setText("60 cal");

        // Lunch
        tvLunchTime.setText("12:30 - 2:00 pm");
        tvLunchEmoji.setText("🥗");
        tvLunchName.setText("Quinoa Bowl");
        tvLunchCal.setText("480 cal");

        // Dinner
        tvDinnerTime.setText("7:00 - 8:30 pm");
        tvDinnerEmoji.setText("🍛");
        tvDinnerName.setText("Moong Dal and Brown Rice");
        tvDinnerCal.setText("520 cal");
    }

    /**
     * setupDayTabs()
     *
     * Stores all 5 day tabs in an array.
     * Mon  → already on this screen, do nothing
     * Tue  → navigates to MealPlanTuesdayActivity
     * Wed/Thu/Fri → shows "coming soon" toast
     */
    private void setupDayTabs() {

        // Store all tabs in array for easy background switching
        dayTabs = new LinearLayout[]{
                findViewById(R.id.tabMon),
                findViewById(R.id.tabTue),
                findViewById(R.id.tabWed),
                findViewById(R.id.tabThu),
                findViewById(R.id.tabFri)
        };

        // Mon — already on this screen
        dayTabs[0].setOnClickListener(v -> { });

        // Tue — go to Tuesday screen
        dayTabs[1].setOnClickListener(v -> {
            Intent intent = new Intent(this, MealPlanTuesdayActivity.class);
            startActivity(intent);
        });

        // Wed — coming soon
        dayTabs[2].setOnClickListener(v ->
                Toast.makeText(this,
                        "Wednesday coming soon!",
                        Toast.LENGTH_SHORT).show());

        // Thu — coming soon
        dayTabs[3].setOnClickListener(v ->
                Toast.makeText(this,
                        "Thursday coming soon!",
                        Toast.LENGTH_SHORT).show());

        // Fri — coming soon
        dayTabs[4].setOnClickListener(v ->
                Toast.makeText(this,
                        "Friday coming soon!",
                        Toast.LENGTH_SHORT).show());
    }

    /**
     * setActiveDay(selectedIndex)
     *
     * Highlights the selected day tab green
     * and resets all other tabs to white.
     *
     * @param selectedIndex 0=Mon, 1=Tue, 2=Wed, 3=Thu, 4=Fri
     */
    private void setActiveDay(int selectedIndex) {
        for (int i = 0; i < dayTabs.length; i++) {
            if (i == selectedIndex) {
                // Active tab — green background, white text
                dayTabs[i].setBackgroundResource(R.drawable.bg_day_active);
                updateTabTextColors(dayTabs[i], "#FFFFFF", "#FFFFFF");
            } else {
                // Inactive tab — white background, dark text
                dayTabs[i].setBackgroundResource(R.drawable.bg_day_inactive);
                updateTabTextColors(dayTabs[i], "#333333", "#777777");
            }
        }
    }

    /**
     * updateTabTextColors(tab, numberColor, nameColor)
     *
     * Changes text color of the day number and day name
     * inside a tab layout.
     * Child 0 = day number TextView (e.g. "1")
     * Child 1 = day name TextView  (e.g. "Mon")
     */
    private void updateTabTextColors(LinearLayout tab,
                                     String numberColor,
                                     String nameColor) {
        TextView tvNumber = (TextView) tab.getChildAt(0);
        TextView tvName   = (TextView) tab.getChildAt(1);

        if (tvNumber != null)
            tvNumber.setTextColor(
                    android.graphics.Color.parseColor(numberColor));
        if (tvName != null)
            tvName.setTextColor(
                    android.graphics.Color.parseColor(nameColor));
    }

    /**
     * setupBottomNav()
     *
     * Wires the 4 bottom navigation tabs.
     * Plans tab is active on this screen.
     */
    private void setupBottomNav() {

        // Home tab
        findViewById(R.id.tabHome).setOnClickListener(v ->
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show());

        // Plans tab — already here
        findViewById(R.id.tabPlans).setOnClickListener(v -> { });

        // Progress tab
        findViewById(R.id.tabProgress).setOnClickListener(v ->
                Toast.makeText(this, "Progress", Toast.LENGTH_SHORT).show());

        // Profile tab
        findViewById(R.id.tabProfile).setOnClickListener(v ->
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show());
    }
}