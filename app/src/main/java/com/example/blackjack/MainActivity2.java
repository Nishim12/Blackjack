package com.example.blackjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<ArrayList<Integer>> cards;
    int turnNo;
    int dealerTurnNo;
    int[] points;
    int A_no;
    int A_player_no;
    int gameStatus=0;
    Runnable delayBeforeIfElse = null;

    RandomNumberGenerator randomNumberGenerator =new RandomNumberGenerator();
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_MONEY = "money";
    int money;
    TextView moneyRealTime;
    int moneyInvested;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Retrieve the money the player has saved locally in his device.
        money = retrieveMoneyFromSharedPreferences();
        moneyRealTime = findViewById(R.id.textView10);
        moneyInvested = getIntent().getIntExtra("money_invested", 0);
        money = money - moneyInvested;

        //Displays the current money the player has
        moneyRealTime.setText(String.valueOf(money));

        ArrayList<Integer> player = new ArrayList<>();
        ArrayList<Integer> dealer = new ArrayList<>();
        points = new int[2];
        cards = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            if (i < 2) {
                player.add(randomNumberGenerator.randomNumber());
            } else {
                dealer.add(randomNumberGenerator.randomNumber());
            }
        }

        A_player_no=0;
        cards.add(player);
        cards.add(dealer);

        //Displays the card that the user and the dealer got on screen.
        cardsDisplayer(cards.get(0).get(0), 4);
        cardsDisplayer(cards.get(0).get(1), 3);
        cardsDisplayer(cards.get(1).get(0), 10);
        ImageView myImageView = findViewById(R.id.imageView9);
        myImageView.setImageResource(R.drawable.card_back_side);

        //Caculates initial points of player
        points[0] = PointSystem.CalculatePoints(cards, 0);
        TextView pointsPlayer = findViewById(R.id.textView5);

        //Sets the total initial points of player.
        if(checkA(0)) {
            pointsPlayer.setText(String.valueOf(points[0])+" / "+String.valueOf(points[0]-11+1));
        }
        else{
            pointsPlayer.setText(String.valueOf(points[0]));
        }

        points[1] = PointSystem.CalculatePoints(cards, 1);
        turnNo = 1;
        dealerTurnNo = 1;

        ImageView imageView14 = findViewById(R.id.imageView14);
        /*This inner class is executed when the player clicks the Hits image.
         * First generates random card using my random Number Generator class written by.
         * Then displays that image on the User-Interface*/
        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameStatus == 0) {
                    // Generate a random card for the player and add it to the player's hand.
                    cards.get(0).add(randomNumberGenerator.randomNumber());

                    // Update the player's hand display.
                    if (turnNo == 1) {
                        cardsDisplayer(cards.get(0).get(2), 2);
                    } else if (turnNo == 2) {
                        cardsDisplayer(cards.get(0).get(3), 5);
                    } else if (turnNo == 3) {
                        cardsDisplayer(cards.get(0).get(4), 1);
                    } else if (turnNo == 4) {
                        cardsDisplayer(cards.get(0).get(5), 6);
                    }

                    points[0] = PointSystem.CalculatePoints(cards, 0);
                    TextView pointsPlayer = findViewById(R.id.textView5);

                    if(checkA(0) && points[0]<=21) {
                        pointsPlayer.setText(String.valueOf(points[0])+" / "+String.valueOf(points[0]-11+1));
                    }
                    else if(checkA(0)){
                        pointsPlayer.setText(String.valueOf(points[0]-11+1));
                    }
                    else{
                        pointsPlayer.setText(String.valueOf(points[0]));
                    }
                    turnNo++;

                    //Checks if the points are not exceeding 21.
                    if (!check(0) && (checkA(0)==false || (checkA(0)==true && (points[0]-11+1)>21))) {
                        TextView status = findViewById(R.id.TextView);
                        status.setText("Bust");
                        saveMoneyToSharedPreferences(money);
                        gameStatus++;
                        navigateToMainActivity3();
                    }
                }
            }
        });

        A_no=0;
        ImageView imageView15 = findViewById(R.id.imageView15);
        /*Updates the points of Player
         * Than displays the cards for the dealer*/
        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkA(0) && points[0]<=21){
                    pointsPlayer.setText(String.valueOf(points[0]));
                }
                else if(checkA(0) && points[0]>21){
                    pointsPlayer.setText(String.valueOf(points[0]-11+1));
                }

                cardsDisplayer(cards.get(1).get(1), 9);

                        // Simulate the dealer's turns until the points reach 17 or more
                        while (gameStatus == 0 && ((checkA(1)==false && points[1] < 17) || (checkA(1)==true && (points[1]-11+1)<17))) {
                            if (points[1] > 17 && points[1] <= 21) {
                                gameStatus++;
                            }
                            else {
                                // Generate a random card for the dealer and add it to the dealer's hand
                                cards.get(1).add(randomNumberGenerator.randomNumber());

                                // Update the dealer's hand display
                                if (dealerTurnNo == 1) {
                                    cardsDisplayer(cards.get(1).get(2), 8);
                                } else if (dealerTurnNo == 2) {
                                    cardsDisplayer(cards.get(1).get(3), 11);
                                } else if (dealerTurnNo == 3) {
                                    cardsDisplayer(cards.get(1).get(4), 7);
                                } else if (dealerTurnNo == 4) {
                                    cardsDisplayer(cards.get(1).get(5), 12);
                                }

                                // Calculate the new points for the dealer
                                points[1] = PointSystem.CalculatePoints(cards, 1);

                                if (checkA(1) && points[1] > 21 && A_no == 0) {
                                    points[1] += 1 - 11;
                                    A_no++;
                                }
                                dealerTurnNo++;
                            }
                        }

                  // Check the result after the dealer's turn.
                  checkResult();
                }
        });

    }

    /**Checks the player points and informs the player whether he won,draw or lost.
     * Than goes to the next Activity MainActivity3*/
    private void checkResult() {
        int playerPoints = PointSystem.CalculatePoints(cards,0);

        if (checkA(0) && playerPoints>21) {
            playerPoints=playerPoints-11+1;
        }
        int dealerPoints = PointSystem.CalculatePoints(cards, 1);

        if (checkA(1) && dealerPoints>21) {
            dealerPoints=dealerPoints-11+1;
        }

        // Check the result and update the game status accordingly
        if (playerPoints > 21 || (dealerPoints <= 21 && dealerPoints > playerPoints)) {
            TextView status = findViewById(R.id.TextView);
            status.setText("Dealer wins!");
            gameStatus++;
        } else if (dealerPoints > 21 || playerPoints > dealerPoints) {
            TextView status = findViewById(R.id.TextView);
            status.setText("Player wins!");
            money=money+2*moneyInvested;
            moneyRealTime.setText(String.valueOf(money));
            gameStatus++;
        } else {
            TextView status = findViewById(R.id.TextView);
            money=money+moneyInvested;
            moneyRealTime.setText(String.valueOf(money));
            status.setText("Draw!");
            gameStatus++;
        }
        saveMoneyToSharedPreferences(money);
        navigateToMainActivity3();
    }

    /**Stores the total money and stores it on the local storage of the application
     * Returns:Total money of player*/
    private int retrieveMoneyFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_MONEY, 2000); // Default value is 2000 if not found
    }

    /**Displays the right card based on input
     * Params:randomNumber-Is the random playing card
     *       imagePosition- Tells what image id to display on the UI*/
    public void cardsDisplayer(int randomNumber, int imagePosition) {
        int[] cardsDisplay = {
                R.drawable.clubs_ace, R.drawable.clubs2, R.drawable.clubs3, R.drawable.clubs4,
                R.drawable.clubs5, R.drawable.clubs6, R.drawable.clubs7, R.drawable.clubs8,
                R.drawable.clubs9, R.drawable.clubs10, R.drawable.clubs_jack, R.drawable.clubs_queen,
                R.drawable.clubs_king,

                R.drawable.diamonds_ace, R.drawable.diamonds2, R.drawable.diamonds3, R.drawable.diamonds4,
                R.drawable.diamonds5, R.drawable.diamonds6, R.drawable.diamonds7, R.drawable.diamonds8,
                R.drawable.diamonds9, R.drawable.diamonds10, R.drawable.diamonds_jack, R.drawable.diamonds_queen,
                R.drawable.diamonds_king,

                R.drawable.hearts_ace, R.drawable.hearts2, R.drawable.hearts3, R.drawable.hearts4,
                R.drawable.hearts5, R.drawable.hearts6, R.drawable.hearts7, R.drawable.hearts8,
                R.drawable.hearts9, R.drawable.hearts10, R.drawable.hearts_jack, R.drawable.spades_queen,
                R.drawable.hearts_king,

                R.drawable.spades_ace, R.drawable.spades2, R.drawable.spades3, R.drawable.spades4,
                R.drawable.spades5, R.drawable.spades6, R.drawable.spades7, R.drawable.spades8,
                R.drawable.spades9, R.drawable.spades10, R.drawable.spades_jack, R.drawable.spades_queen,
                R.drawable.spades_king
        };
        ImageView imageView = null;
        if(imagePosition==1) {
             imageView = findViewById(R.id.imageView1);
        }

        else if (imagePosition==2) {
            imageView = findViewById(R.id.imageView2);
        }

        else if (imagePosition==3) {
            imageView = findViewById(R.id.imageView3);
        }

        else if (imagePosition==4) {
            imageView = findViewById(R.id.imageView4);
        }

        else if (imagePosition==5) {
            imageView = findViewById(R.id.imageView5);
        }

        else if (imagePosition==6) {
            imageView = findViewById(R.id.imageView6);
        }

        else if (imagePosition==7) {
            imageView = findViewById(R.id.imageView7);
        }

        else if (imagePosition==8) {
            imageView = findViewById(R.id.imageView8);
        }

        else if (imagePosition==9) {
            imageView = findViewById(R.id.imageView9);
        }

        else if (imagePosition==10) {
            imageView = findViewById(R.id.imageView10);
        }

        else if (imagePosition==11) {
            imageView = findViewById(R.id.imageView11);
        }

        else if (imagePosition==12) {
            imageView = findViewById(R.id.imageView12);
        }

        int drawableResourceId = cardsDisplay[randomNumber];
        imageView.setImageResource(drawableResourceId);
    }
    /**Checks if the sum of points are not crossing 21
     * Params : whichPlayer-Whether it is dealer(1 for dealer) or player(0 for player)
     * Returns:true if points are less than 21 or else false*/
    public boolean check(int whichPlayer){
        if(points[whichPlayer]>21){
            return false;
        }
        return true;
    }

    /**Saves money to the local storage of the device
     * Params:money-The final money of the player after game*/
    private void saveMoneyToSharedPreferences(int money) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_MONEY, money);
        editor.apply();
    }

    /**Checks if the amongst the random cards there is an ace
     * Params:playerOrDealer-Whether it is player(0 for player) or dealer's card(1 for dealer)
     * Returns:true if an ace is present and false otherwise*/
    public boolean checkA(int playerOrDealer){
        for(int i=0;i<cards.get(0).size(); i++){
            if(cards.get(playerOrDealer).get(i)==0 || cards.get(playerOrDealer).get(i)==13 || cards.get(playerOrDealer).get(i)==26 || cards.get(playerOrDealer).get(i)==39){
                return true;
            }
        }
        return false;
    }

    /**To go the next Activity which is MainActivity3 after a delay of 3 seconds*/
    public void navigateToMainActivity3(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        }, 3000);
    }
}

