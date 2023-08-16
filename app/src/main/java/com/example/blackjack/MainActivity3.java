package com.example.blackjack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button yesButton = findViewById(R.id.yesButton);
        Button noButton = findViewById(R.id.noButton);

            yesButton.setOnClickListener(v -> {
                // User clicked on "Yes" button, navigate to MainActivity
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            });

            noButton.setOnClickListener(v -> {
                // User clicked on "No" button, close the app
                finishAffinity();
            });
        }

    }
