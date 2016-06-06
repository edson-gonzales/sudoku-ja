package com.sudoku;

import java.util.Scanner;

/**
 * Created by Alejandra on 04/06/2016.
 */
public class Menu {
    //    var manager = new TCManager();
    public void start() {
        Boolean exit = false;
        do {
            System.out.println("-----------Menu-----------" + "\n");
            System.out.println("1.Start Game");
            System.out.println("2.Configuration");
            System.out.println("3.Exit");
            System.out.println("Select an option:");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            switch (option) {
                case 1:
                    Game game = new Game();
                    game.start();
                    break;
                case 2:
                    System.out.println("Configuration Settings");
                    break;
                case 3:
                    System.out.println("Exit");
                    exit = true;
                    break;
                default:
                    System.out.println("ERROR::Please enter a valid selection.");
                    break;
            }
        } while (!exit);
    }
}
