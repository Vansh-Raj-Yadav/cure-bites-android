package com.example.curebites;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ChatNutritionistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This simply loads your new layout without the edge-to-edge crashing code
        setContentView(R.layout.activity_chat_nutritionist);
    }
}