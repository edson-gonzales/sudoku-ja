package com.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;

/**
 * This class represent the Menu console interface
 */
public class Menu extends Console {
    Boolean exit = false;

    private final Map<Integer, Console> menuOptions = new HashMap<>();

    public Menu() throws NoSuchMethodException {
        menuOptions.put(1, new Game());
        menuOptions.put(2, new Configuration());
        menuOptions.put(3, new Resolver());
    }

    private void selectOption(int option) {
        if (menuOptions.containsKey(option)) {
            menuOptions.get(option).start();
        } else {
            displayError();
            start();
        }
    }


    @Override
    public void start() {
        do {
            String title = "Sudoku Menu";
            String option1 = "1.Start Game";
            String option2 = "2.Configuration";
            String option3 = "3.Resolve sudoku";
            String option4 = "4.Exit";
            ArrayList<String> options = new ArrayList<>(Arrays.asList(option1, option2, option3, option4));
            displayOptions(title, options);
            input = new Scanner(System.in);
            int option = input.nextInt();
            if (option == 4) {
                exit = true;
                display("Sudoku game - Exit");
            } else
                selectOption(option);
        }
        while (!exit);
    }
}
