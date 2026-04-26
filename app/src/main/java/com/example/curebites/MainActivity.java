package com.example.curebites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Find the buttons from your XML design using their IDs
        AppCompatButton btnGetStarted = findViewById(R.id.btnGetStarted);
        TextView btnLogin = findViewById(R.id.btnLogin);

        // 2. "Get Started" takes the user to the 3-page slider
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OnboardingActivity.class);
                startActivity(intent);
            }
        });

        // 3. "Login" takes the user to the Authentication screen
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}