package com.example.curebites; // Replace with your actual package name

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This line links the Java code to your activity_home.xml layout
        setContentView(R.layout.activity_home);
    }
}