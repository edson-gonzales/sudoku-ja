package com.gui;

import com.utils.writers.PropertiesWriter;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;


/**
 * This class represent the Configuration console interface
 */
public class Configuration extends Console {
    private Boolean exit = false;

    private final Map<Integer, Method> menuOptions = new HashMap<>();

    private static final Map<Integer, String> ALGORITHM_OPTIONS = new HashMap<>();

    static {
        ALGORITHM_OPTIONS.put(1, "Backtracking");
        ALGORITHM_OPTIONS.put(2, "Peter Norvig");
        ALGORITHM_OPTIONS.put(3, "Exact Cover");
    }

    private static final Map<Integer, String> LEVELS_OPTIONS = new HashMap<>();

    static {
        LEVELS_OPTIONS.put(1, "Easy");
        LEVELS_OPTIONS.put(2, "Medium");
        LEVELS_OPTIONS.put(3, "Hard");
        LEVELS_OPTIONS.put(4, "Custom");
    }

    public Configuration() throws NoSuchMethodException {
        super();
        menuOptions.put(1, Configuration.class.getMethod("enterOutputType"));
        menuOptions.put(2, Configuration.class.getMethod("startSelectLevel"));
        menuOptions.put(3, Configuration.class.getMethod("startSelectAlgorithm"));
        menuOptions.put(4, Configuration.class.getMethod("enterCharacter"));
        menuOptions.put(5, Configuration.class.getMethod("exit"));
    }

    /**
     * Start the Configuration console interface
     */
    @Override
    public void start() {
        do {
            String title = "Configuration Settings";
            String option1 = "1.Output type";
            String option2 = "2.Level";
            String option3 = "3.Algorithm";
            String option4 = "4.Interface";
            String option5 = "5.Exit";
            ArrayList<String> options = new ArrayList<>(Arrays.asList(option1, option2, option3, option4, option5));
            displayOptions(title, options);
            input = new Scanner(System.in);
            int option = input.nextInt();
            selectOption(option);
        } while (!exit);
    }

    private void selectOption(int option) {
        if (menuOptions.containsKey(option)) {
            try {
                menuOptions.get(option).invoke(this, null);
            } catch (IllegalAccessException e) {
                Logger.getLogger(Configuration.class).error("The method is inaccessible", e);
            } catch (InvocationTargetException e) {
                Logger.getLogger(Configuration.class).error("The method throws an exception.", e);
            }
        } else {
            displayError();
            start();
        }
    }

    public void enterCharacter() {
        display("Enter a character that represents an empty cell:");
        String character = input.next();
        propertiesWriter.setProperty(PropertiesWriter.CHARACTER, character);
    }

    public void startSelectLevel() {
        String title = "Levels";
        String option1 = "1.Easy";
        String option2 = "2.Medium";
        String option3 = "3.Hard";
        String option4 = "4.Custom";
        ArrayList<String> options = new ArrayList<>(Arrays.asList(option1, option2, option3, option4));
        displayOptions(title, options);
        int option = input.nextInt();
        selectLevel(option);
    }

    private void selectLevel(int option) {
        if (LEVELS_OPTIONS.containsKey(option)) {
            String value = LEVELS_OPTIONS.get(option);
            propertiesWriter.setProperty(PropertiesWriter.LEVEL, value);
            if (value.equals("Custom")) {
                enterCustomLimits();
            }
        } else {
            displayError();
            startSelectLevel();
        }
    }

    private void enterCustomLimits() {
        display("Enter min limit:");
        String min = input.next();
        propertiesWriter.setProperty(PropertiesWriter.CUSTOM_MIN, min);
        display("Enter max limit");
        String max = input.next();
        propertiesWriter.setProperty(PropertiesWriter.CUSTOM_MAX, max);
    }

    public void startSelectAlgorithm() {
        String title = "Algorithm";
        String option1 = "1.Backtracking";
        String option2 = "2.Peter Norvig";
        String option3 = "3.Exact Cover";
        ArrayList<String> options = new ArrayList<>(Arrays.asList(option1, option2, option3));
        displayOptions(title, options);
        int option = input.nextInt();
        selectAlgorithm(option);
    }

    private void selectAlgorithm(int option) {
        if (ALGORITHM_OPTIONS.containsKey(option)) {
            String value = ALGORITHM_OPTIONS.get(option);
            propertiesWriter.setProperty(PropertiesWriter.ALGORITHM, value);
        } else {
            displayError();
            startSelectAlgorithm();
        }
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
