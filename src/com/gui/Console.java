package com.gui;

import java.util.Scanner;

/**
 * This class represent the Game console interface
 */
public class Console {

    protected Scanner input = new Scanner(System.in);

    /**
     * Display a text
     *
     * @param text The text to be shown
     */
    protected void display(String text) {
        System.out.println(text);
    }
}
