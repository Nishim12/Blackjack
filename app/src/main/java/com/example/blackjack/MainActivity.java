package com.example.blackjack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int money;
    private int moneyInvested = 0;
    private boolean firstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView6 = findViewById(R.id.imageView6);
        ImageView imageView7 = findViewById(R.id.imageView7);
        ImageView imageView8 = findViewById(R.id.imageView8);
        ImageView imageView10 = findViewById(R.id.imageView10);
        ImageView imageView12 = findViewById(R.id.imageView12);
        ImageView imageView11 = findViewById(R.id.imageView11);
        ImageView imageView13 = findViewById(R.id.imageView13);

        // Set OnClickListener for each ImageView
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vibrate();
                showImage(v);
                moneyInvested += 5;
                updateTextViews("Cancel", "Deal", Color.WHITE);
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vibrate();
                showImage(v);
                moneyInvested += 10;
                updateTextViews("Cancel", "Deal", Color.WHITE);
            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vibrate();
                showImage(v);
                moneyInvested += 25;
                updateTextViews("Cancel", "Deal", Color.WHITE);
            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vibrate();
                showImage(v);
                moneyInvested += 50;
                updateTextViews("Cancel", "Deal", Color.WHITE);
            }
        });

        // Set OnClickListener for imageView12
        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneyInvested = 0;
                imageView12.setImageDrawable(null);
                imageView11.setImageDrawable(null);
                imageView13.setImageDrawable(null);
                updateTextViews("", "", Color.parseColor("#FF0000"));
                firstClick = true;
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText("");
            }
        });

        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                vibrate();
                // Start the new activity when imageView11 is clicked
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("money_invested", moneyInvested);
                startActivity(intent);
            }
        });
    }

    private void showImage(View v) {
        // Get the clicked ImageView and its source drawable
        ImageView clickedImageView = (ImageView) v;
        Drawable imageDrawable = clickedImageView.getDrawable();

        // Set the drawable to imageView13
        ImageView imageView13 = findViewById(R.id.imageView13);
        imageView13.setImageDrawable(imageDrawable);

        // Update imageView11 and imageView12 on first click
        if (firstClick) {
            ImageView imageView11 = findViewById(R.id.imageView11);
            ImageView imageView12 = findViewById(R.id.imageView12);
            imageView11.setImageResource(R.drawable.greenplay);
            imageView12.setImageResource(R.drawable.redcross);
            firstClick = false;
        }
    }


    private void updateTextViews(String text1, String text2, int textColor) {
        TextView textView = findViewById(R.id.textView);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textView2 = findViewById(R.id.textView2);

        textView.setText(text1);
        textView.setTextColor(textColor);

        textView3.setText(text2);
        textView3.setTextColor(textColor);

        textView2.setText(String.valueOf(moneyInvested));
        textView2.setTextColor(Color.WHITE);
    }


//    private void vibrate() {
//        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        if (vibrator != null) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                // Vibrate with a custom pattern (milliseconds)
//                long[] pattern = {0, 100, 50, 100};
//                vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1));
//            } else {
//                // Vibrate for a fixed time (milliseconds)
//                vibrator.vibrate(100);
//            }
//        }
//    }
}