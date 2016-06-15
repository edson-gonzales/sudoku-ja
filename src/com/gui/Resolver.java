package com.gui;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.algorithm.PeterNorvig;
import com.sudoku.SudokuBoard;
import com.utils.TimeManager;
import com.utils.readers.SudokuReader;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represent the Resolver console interface
 */
public class Resolver extends Console {
    TimeManager timeManager = new TimeManager();
    private Boolean exit = false;

    private final Map<Integer, Method> MENU_OPTIONS = new HashMap<>();
    {
        MENU_OPTIONS.put(1, Resolver.class.getMethod("resolveFromFile"));
        MENU_OPTIONS.put(2, Resolver.class.getMethod("resolveFromConsole"));
        MENU_OPTIONS.put(3, Resolver.class.getMethod("exit"));
    }

    private static final Map<Integer, Algorithm> ALGORITHM_OPTIONS = new HashMap<>();
    static {
        ALGORITHM_OPTIONS.put(1, new Backtracking());
        ALGORITHM_OPTIONS.put(2, new PeterNorvig());
    }

    public Resolver() throws NoSuchMethodException {
        //Do nothing
    }

    /**
     * Start the Resolver console interface
     */
    public void start() {

        do {
            display("------Resolver Sudoku------" + "\n");
            display("1.Read from a CSV/TXT file");
            display("2.Read from Console");
            display("3.Exit");
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
            Logger.getLogger(Resolver.class).error("The method is inaccessible", e);
        } catch (InvocationTargetException e) {
            Logger.getLogger(Resolver.class).error("The method throws an exception.", e);
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
        SudokuBoard board = reader.getSudokuBoard();
        return board;
    }

    private SudokuBoard getSudokuFromConsole(String sudokuString) {
        SudokuReader reader = new SudokuReader();
        return reader.getSudokuBoardFromString(sudokuString);
    }

    private Algorithm getAlgorithm() {
        display("--------Algorithm---------");
        display("1.Backtracking");
        display("2.Peter Norvig");
        display("3.Other");
        display("Select an option:");
        int option = input.nextInt();
        return ALGORITHM_OPTIONS.get(option);
    }

    private void resolveSudoku(Algorithm algorithm, SudokuBoard sudokuBoard) {
        timeManager.start();
        algorithm.solve(sudokuBoard);
        timeManager.stop();
        display(sudokuBoard.toString());
        display("Time:" + timeManager.getMilliseconds() + " ms.");
    }

    public void exit() {
        this.exit = true;
    }

    public void resolveFromConsole() {
        String sudokuString = enterSudoku();
        SudokuBoard sudokuBoard = getSudokuFromConsole(sudokuString);
        if(sudokuBoard == null) {
            display("Error:: The size is incorrect!");
            resolveFromConsole();
        }
        else {
            Algorithm algorithm = getAlgorithm();
            resolveSudoku(algorithm, sudokuBoard);
        }
    }
}
