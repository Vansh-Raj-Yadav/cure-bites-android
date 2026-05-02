package com.example.curebites;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This simply loads your teammate's design without crashing.
        // The edge-to-edge logic has been removed to bypass the R.id.main error.
        setContentView(R.layout.activity_onboarding1);
    }
}