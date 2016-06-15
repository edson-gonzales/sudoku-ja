package com.gui;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.algorithm.PeterNorvig;
import com.algorithm.exactcover.ExactCover;
import com.sudoku.SudokuBoard;
import com.utils.TimeManager;
import com.utils.readers.SudokuReader;
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
 * This class represent the Resolver console interface
 */
public class Resolver extends Console {
    TimeManager timeManager = new TimeManager();
    private Boolean exit = false;

    private final Map<Integer, Method> menuOptions = new HashMap<>();

    private static final Map<String, Algorithm> ALGORITHM_OPTIONS = new HashMap<>();
    static {
        ALGORITHM_OPTIONS.put("Backtracking", new Backtracking());
        ALGORITHM_OPTIONS.put("Peter Norvig", new PeterNorvig());
        ALGORITHM_OPTIONS.put("Exact Cover", new ExactCover());
    }

    public Resolver() throws NoSuchMethodException {
        super();
        menuOptions.put(1, Resolver.class.getMethod("resolveFromFile"));
        menuOptions.put(2, Resolver.class.getMethod("resolveFromConsole"));
        menuOptions.put(3, Resolver.class.getMethod("exit"));
    }

    /**
     * Start the Resolver console interface
     */
    @Override
    public void start() {
        do {
            String title="Resolver Sudoku";
            String option1="1.Read from a CSV/TXT file";
            String option2="2.Read from Console";
            String option3="3.Exit";
            ArrayList<String> options = new ArrayList<>(Arrays.asList(option1, option2, option3));
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
                Logger.getLogger(Resolver.class).error("The method is inaccessible", e);
            } catch (InvocationTargetException e) {
                Logger.getLogger(Resolver.class).error("The method throws an exception.", e);
            }
        } else {
            displayError();
            start();
        }
    }

    public void resolveFromFile() {
        String inputPath = enterInputPath();
        SudokuBoard sudokuBoard = getSudokuFromPath(inputPath);
        if(sudokuBoard == null) {
            display("Error:: The size is incorrect!");
            resolveFromFile();
        }
        else {
            Algorithm algorithm = getAlgorithm();
            resolveSudoku(algorithm, sudokuBoard);
        }
    }

    private String enterInputPath() {
        display("Enter the input file path:");
        return input.next();
    }

    private String enterSudoku() {
        display("Enter sudoku string:");
        return input.next();
    }

    private SudokuBoard getSudokuFromPath(String inputPath) {
        SudokuReader reader = new SudokuReader(inputPath);
        return reader.getSudokuBoard();
    }

    private SudokuBoard getSudokuFromConsole(String sudokuString) {
        SudokuReader reader = new SudokuReader();
        return reader.getSudokuBoardFromString(sudokuString);
    }

    private Algorithm getAlgorithm() {
        String option = propertiesReader.getProperty(PropertiesWriter.ALGORITHM);
        return ALGORITHM_OPTIONS.get(option);
    }

    private void resolveSudoku(Algorithm algorithm, SudokuBoard sudokuBoard) {
        timeManager.start();
        algorithm.solve(sudokuBoard);
        timeManager.stop();
        display(sudokuBoard.toString());
        display("Algorithm: " + propertiesReader.getProperty(PropertiesWriter.ALGORITHM));
        display("Time: " + timeManager.getMilliseconds() + " ms.");
    }

    public void exit() {
        this.exit = true;
    }

    public void resolveFromConsole() {
        String sudokuString = enterSudoku();
        SudokuBoard sudokuBoard = getSudokuFromConsole(sudokuString);
        if(sudokuBoard == null) {
            display("ERROR:: The size is incorrect!");
            resolveFromConsole();
        }
        else {
            Algorithm algorithm = getAlgorithm();
            resolveSudoku(algorithm, sudokuBoard);
        }
    }
}
