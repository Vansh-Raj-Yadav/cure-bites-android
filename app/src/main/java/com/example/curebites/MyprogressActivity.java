package com.example.curebites;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

class MyProgressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprogress);

        // Back arrow click
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Bottom nav clicks (stub)
        int[] navIds = {R.id.navHome, R.id.navPlans, R.id.navProgress, R.id.navProfile};
        for (int id : navIds) {
            findViewById(id).setOnClickListener(v -> {
                // Handle navigation here
            });
        }
    }
}
