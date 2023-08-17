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

            /**If the player clicks on yes button than he is re-directed to MainActivity*/
            yesButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            });

            /**If the user clicks on No button than the player exits the app*/
            noButton.setOnClickListener(v -> {
                finishAffinity();
            });
        }
    }
