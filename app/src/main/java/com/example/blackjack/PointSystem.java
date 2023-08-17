package com.example.blackjack;

import java.util.ArrayList;

public class PointSystem {
    static int A;
    /**Calculates the total points of dealer or player.
     * Params:cardsStore-Double dimensional arrayList that contains all the cards
     *                   of player and dealer.
     *        playerOrDealer-Whether it is a player(0 for player) or dealer(1 for dealer).
     *Return:Returns the points of the respective dealer or player.*/
    public static int CalculatePoints(ArrayList<ArrayList<Integer>> cardsStore,int playerOrDealer){
        int points=0;
        A=0;
        for (int i=0; i<cardsStore.get(playerOrDealer).size();i++){
            if (cardsStore.get(playerOrDealer).get(i)<=12) {
                points = points + pointsClassifier(cardsStore.get(playerOrDealer).get(i));
            }
            else if (cardsStore.get(playerOrDealer).get(i)<=25) {
                points += pointsClassifier(cardsStore.get(playerOrDealer).get(i) - 13);
            }
            else if (cardsStore.get(playerOrDealer).get(i)<=38) {
                points += pointsClassifier(cardsStore.get(playerOrDealer).get(i) - 26);
            }
            else {
                points += pointsClassifier(cardsStore.get(playerOrDealer).get(i) - 39);
            }
        }

        return points;
    }

    public static int pointsClassifier(int individualCard){
        if (individualCard<=9 && individualCard!=0)
            return ++individualCard;

        else if (individualCard == 0 && A==0) {
            A++;
            return 11;
        }
        else if(A>0 && individualCard==0){
            return 1;
        }
        else
            return 10;
    }
}
