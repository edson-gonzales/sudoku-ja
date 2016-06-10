package com.gui;

import com.utils.writers.PropertiesWriter;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represent the Configuration console interface
 */
public class Configuration extends Console {
    private PropertiesWriter propertiesWriter = new PropertiesWriter();
    private Boolean exit = false;

    private final Map<Integer, Method> MENU_OPTIONS = new HashMap<>();
    {
        MENU_OPTIONS.put(1, Configuration.class.getMethod("enterOutputType"));
        MENU_OPTIONS.put(2, Configuration.class.getMethod("startSelectLevel"));
        MENU_OPTIONS.put(3, Configuration.class.getMethod("startSelectAlgorithm"));
        MENU_OPTIONS.put(4, Configuration.class.getMethod("enterCharacter"));
        MENU_OPTIONS.put(5, Configuration.class.getMethod("exit"));
    }

    private static final Map<Integer, String> ALGORITHM_OPTIONS = new HashMap<>();
    static {
        ALGORITHM_OPTIONS.put(1, "Backtracking");
        ALGORITHM_OPTIONS.put(2, "Peter Norvig");
        ALGORITHM_OPTIONS.put(3, "Other");
    }

    private static final Map<Integer, String> LEVELS_OPTIONS = new HashMap<>();
    static {
        LEVELS_OPTIONS.put(1, "Easy");
        LEVELS_OPTIONS.put(2, "Medium");
        LEVELS_OPTIONS.put(3, "Hard");
        LEVELS_OPTIONS.put(4, "Custom");
    }

    public Configuration() throws NoSuchMethodException {
        //Do nothing
    }

    /**
     * Start the Configuration console interface
     */
    public void start() {

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
            selectOption(option);
        } while (!exit);
    }

    private void selectOption(int option) {
        try {
            MENU_OPTIONS.get(option).invoke(this, null);
        } catch (IllegalAccessException e) {
            Logger.getLogger(Configuration.class).error("The method is inaccessible", e);
        } catch (InvocationTargetException e) {
            Logger.getLogger(Configuration.class).error("The method throws an exception.", e);
        }
    }

    public void enterCharacter() {
        display("Enter a character that represents an empty cell:");
        String character = input.next();
        propertiesWriter.setProperty(PropertiesWriter.CHARACTER, character);
    }

    public void startSelectLevel() {
        display("--------Levels---------");
        display("1.Easy");
        display("2.Medium");
        display("3.Hard");
        display("4.Custom");
        display("Select an option:");
        int option = input.nextInt();
        selectLevel(option);
    }

    private void selectLevel(int option) {
        String value = LEVELS_OPTIONS.get(option);
        propertiesWriter.setProperty(PropertiesWriter.LEVEL, value);
    }

    public void startSelectAlgorithm() {
        display("--------Algorithm---------");
        display("1.Backtracking");
        display("2.Peter Norvig");
        display("3.Other");
        display("Select an option:");
        int option = input.nextInt();
        selectAlgorithm(option);
    }

    private void selectAlgorithm(int option) {
        String value = ALGORITHM_OPTIONS.get(option);
        propertiesWriter.setProperty(PropertiesWriter.ALGORITHM, value);
    }

    public void enterOutputType() {
        display("Enter the path of the output file:");
        String outputPath = input.next();
        propertiesWriter.setProperty(PropertiesWriter.OUTPUT_PATH, outputPath);
        display("Enter the file name:");
        String outputFileName = input.next();
        propertiesWriter.setProperty(PropertiesWriter.OUTPUT_FILE_NAME, outputFileName);
    }

    public void exit() {
        this.exit = true;
    }
}
