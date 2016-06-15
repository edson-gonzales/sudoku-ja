package com.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represent the Menu console interface
 */
public class Menu extends Console {
    private static final Map<Integer, Console> MENU_OPTIONS = new HashMap<>();
    {
        MENU_OPTIONS.put(1, new Game());
        MENU_OPTIONS.put(2, new Configuration());
        MENU_OPTIONS.put(3, new Resolver());
    }

    private void selectOption(int option) {
        MENU_OPTIONS.get(option).start();
    }

    public Menu() throws NoSuchMethodException {
    }

    public void start() {
        Boolean exit = false;
        do {
            display("-----------Menu-----------" + "\n");
            display("1.Start Game");
            display("2.Configuration");
            display("3.Resolve sudoku");
            display("4.Exit");
            display("Select an option:");
            input = new Scanner(System.in);
            int option = input.nextInt();
            if (option == 4)
                exit = true;
            else
                selectOption(option);
        }
        while (!exit);
    }
}
