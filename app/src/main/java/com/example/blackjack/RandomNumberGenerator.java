package com.example.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator {
    private static List<Integer> numbers = new ArrayList<>();
    private static int currentIndex = 0;

    public static int randomNumber() {
        if (numbers.isEmpty()) {
            // Initialize the list with numbers from 1 to 52 and shuffle it
            for (int i = 1; i <= 52; i++) {
                numbers.add(i);
            }
            Collections.shuffle(numbers);
            currentIndex = numbers.size() - 1;
        }

        // Get the last element from the list (random number) and remove it
        int randomNumber = numbers.get(currentIndex);
        numbers.remove(currentIndex);
        currentIndex--;

        return randomNumber;
    }
}