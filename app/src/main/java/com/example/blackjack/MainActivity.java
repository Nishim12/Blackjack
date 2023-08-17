package com.example.blackjack;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

        //If the user clicks on 5 coin than subtract that amount from current money of user
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(v);
                moneyInvested += 5;
                updateTextViews("Cancel", "Deal", Color.WHITE);
            }
        });

        //If the user clicks on 10 coin than subtract that amount from current money of user
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(v);
                moneyInvested += 10;
                updateTextViews("Cancel", "Deal", Color.WHITE);
            }
        });

        //If the user clicks on 25 coin than subtract that amount from current money of user
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(v);
                moneyInvested += 25;
                updateTextViews("Cancel", "Deal", Color.WHITE);
            }
        });

        //If the user clicks on 50 coin than subtract that amount from current money of user
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage(v);
                moneyInvested += 50;
                updateTextViews("Cancel", "Deal", Color.WHITE);
            }
        });

        //If the user clciks on cancel button than restart the money pool for user
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

        //Go the next Activity where user can play the Blackjack game if he clicks on next button
        //to confirm his money invested in the game
        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        // Show the photo of the money the user clicks on in id imageView13
        ImageView imageView13 = findViewById(R.id.imageView13);
        imageView13.setImageDrawable(imageDrawable);

        // As soon as the user clicks on the money he invested than display an icon to restart
        //the deal or proceed to the next Activity to play the game
        if (firstClick) {
            ImageView imageView11 = findViewById(R.id.imageView11);
            ImageView imageView12 = findViewById(R.id.imageView12);
            imageView11.setImageResource(R.drawable.greenplay);
            imageView12.setImageResource(R.drawable.redcross);
            firstClick = false;
        }
    }

    //Display the current sum of money pool which the user will play the Blackjack game
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

}