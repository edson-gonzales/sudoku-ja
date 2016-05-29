package com.utils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that represent the Number Generator
 */
public class NumberGenerator {

    /**
     * Given there is nine digits from 1 to 9, this method gives one digit randomly
     *
     * @return A digit
     */
    public static Integer getADigit() {
        int iniDigit = 1;
        int endDigit = 9;
        return generate(iniDigit, endDigit);
    }

    /**
     * Generate a range of numbers between [min... max]
     *
     * @param max The maximum number
     * @return The list of random numbers generated without repetition
     */
    public static ArrayList<Integer> generate(int min, int max, int size) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < size) {
            int random = randomGenerator.nextInt((max - min) + 1) + min;
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers;
    }

    /**
     * Generate a number between [min... max]
     *
     * @param max The maximum number
     * @param min The minimum number
     * @return A number
     */
    public static Integer generate(int min, int max) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt((max - min) + 1) + min;
    }
}
