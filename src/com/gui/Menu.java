package com.gui;

import java.util.Scanner;

/**
 * This class represent the Menu console interface
 */
public class Menu extends Console{
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
            switch (option) {
                case 1:
                    Game game = new Game();
                    game.start();
                    break;
                case 2:
                    Configuration configuration = null;
                    try {
                        configuration = new Configuration();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (configuration != null) {
                        configuration.start();
                    }
                    break;
                case 3:
                    Resolver resolver = null;
                    try {
                        resolver = new Resolver();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (resolver != null) {
                        resolver.start();
                    }
                    break;
                case 4:
                    display("Exit");
                    exit = true;
                    break;
                default:
                    display("ERROR::Please enter a valid selection.");
                    break;
            }
        } while (!exit);
    }
}
