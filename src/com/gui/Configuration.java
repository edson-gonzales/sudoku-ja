package com.gui;

import com.utils.writers.PropertiesWriter;

import java.util.Scanner;

/**
 * This class represent the Configuration console interface
 */
public class Configuration extends Console {
    private PropertiesWriter propertiesWriter = new PropertiesWriter();

    public void start() {
        Boolean exit = false;
        do {
            display("------Configuration Settings------" + "\n");
            display("1.Output type");
            display("2.Level");
            display("3.Algorithm");
            display("4.Interface");
            display("5.Exit");
            display("Select an option:");
            input = new Scanner(System.in);
            int option = input.nextInt();
            switch (option) {
                case 1:
                    enterOutputType();
                    break;
                case 2:
                    selectLevel();
                    break;
                case 3:
                    selectAlgorithm();
                    break;
                case 4:
                    enterCharacter();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    display("ERROR::Please enter a valid selection.");
                    break;
            }
        } while (!exit);
    }


    private void enterCharacter() {
        display("Enter a character that represents an empty cell:");
        String character = input.next();
        propertiesWriter.setProperty(PropertiesWriter.CHARACTER, character);
    }

    private void selectLevel() {
        display("--------Levels---------");
        display("1.Easy");
        display("2.Medium");
        display("3.Hard");
        display("4.Custom");
        display("5.Exit");
        display("Select an option:");
        int option = input.nextInt();
        switch (option) {
            case 1:
                propertiesWriter.setProperty(PropertiesWriter.LEVEL, "Easy");
                break;
            case 2:
                propertiesWriter.setProperty(PropertiesWriter.LEVEL, "Medium");
                break;
            case 3:
                propertiesWriter.setProperty(PropertiesWriter.LEVEL, "Hard");
                break;
            case 4:
                propertiesWriter.setProperty(PropertiesWriter.LEVEL, "Custom");
                break;
            case 5:
                break;
            default:
                display("ERROR::Please enter a valid selection.");
                break;
        }
    }

    private void selectAlgorithm() {
        display("--------Algorithm---------");
        display("1.Backtracking");
        display("2.Peter Norvig");
        display("3.Other");
        display("4.Exit");
        display("Select an option:");
        int option = input.nextInt();
        switch (option) {
            case 1:
                propertiesWriter.setProperty(PropertiesWriter.ALGORITHM, "Backtracking");
                break;
            case 2:
                propertiesWriter.setProperty(PropertiesWriter.ALGORITHM, "Peter Norvig");
                break;
            case 3:
                propertiesWriter.setProperty(PropertiesWriter.ALGORITHM, "Other");
                break;
            case 4:
                break;
            default:
                display("ERROR::Please enter a valid selection.");
                break;
        }
    }

    private void enterOutputType() {
        display("Enter the path of the output file:");
        String outputPath = input.next();
        propertiesWriter.setProperty(PropertiesWriter.OUTPUT_PATH, outputPath);
        display("Enter the file name:");
        String outputFileName = input.next();
        propertiesWriter.setProperty(PropertiesWriter.OUTPUT_FILE_NAME, outputFileName);
    }
}
