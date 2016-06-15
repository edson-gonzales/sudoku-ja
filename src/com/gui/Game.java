package com.gui;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.algorithm.PeterNorvig;
import com.sudoku.SudokuBoard;
import com.utils.SudokuGenerator;
import com.utils.writers.PropertiesWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represent the Game console interface
 */
public class Game extends Console{
    private SudokuBoard board;
    private SudokuBoard solved;

    private static final Map<String, Algorithm> ALGORITHM_OPTIONS = new HashMap<>();
    static {
        ALGORITHM_OPTIONS.put("Backtracking", new Backtracking());
        ALGORITHM_OPTIONS.put("Peter Norvig", new PeterNorvig());
    }

    private static final Map<String, String> BOTTOM_LIMIT_LEVELS = new HashMap<>();

    static {
        BOTTOM_LIMIT_LEVELS.put("Easy",PropertiesWriter.EASY_MIN);
        BOTTOM_LIMIT_LEVELS.put("Medium", PropertiesWriter.MEDIUM_MIN);
        BOTTOM_LIMIT_LEVELS.put("Hard", PropertiesWriter.HARD_MIN);
        BOTTOM_LIMIT_LEVELS.put("Custom", PropertiesWriter.CUSTOM_MIN);
    }

    private static final Map<String, String> TOP_LIMIT_LEVELS = new HashMap<>();

    static {
        TOP_LIMIT_LEVELS.put("Easy", PropertiesWriter.EASY_MAX);
        TOP_LIMIT_LEVELS.put("Medium", PropertiesWriter.MEDIUM_MAX);
        TOP_LIMIT_LEVELS.put("Hard", PropertiesWriter.HARD_MAX);
        TOP_LIMIT_LEVELS.put("Custom",PropertiesWriter.CUSTOM_MAX);
    }

    private static final Map<String, Integer> LETTERS = new HashMap<>();

    static {
        LETTERS.put("A", 0);
        LETTERS.put("B", 1);
        LETTERS.put("C", 2);
        LETTERS.put("D", 3);
        LETTERS.put("E", 4);
        LETTERS.put("F", 5);
        LETTERS.put("G", 6);
        LETTERS.put("H", 7);
        LETTERS.put("I", 8);
    }

    /**
     * This is the constructor of Game
     */
    public Game() {
        super();
        generateGameBoard();
        generateSolution();
    }

    private void generateGameBoard(){
        String level = propertiesReader.getProperty("LEVEL");
        String minComplexity = propertiesReader.getProperty(BOTTOM_LIMIT_LEVELS.get(level));
        String maxComplexity = propertiesReader.getProperty(TOP_LIMIT_LEVELS.get(level));
        this.board = SudokuGenerator.generate(Integer.parseInt(minComplexity), Integer.parseInt(maxComplexity));
    }

    private void generateSolution(){
        this.solved = new SudokuBoard(this.board.parseToArray());
        String algorithmType = propertiesReader.getProperty("ALGORITHM");
        Algorithm algorithm = ALGORITHM_OPTIONS.get(algorithmType);
        algorithm.solve(this.solved);
    }

    /**
     * Start the online game
     */
    public void start() {
        Boolean exit = false;
        display("--------Sudoku Game-------\n");
        displayGame();
        do {
            String pos = enterPosition();
            String number = enterNumber();

            switch (number) {
                case "H":
                    writeOnBoard(pos, getHint(pos));
                    displayGame();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    int num = Integer.parseInt(number);
                    writeOnBoard(pos, num);
                    displayGame();
                    break;
            }
        }
        while (this.board.hasAnEmptyCell() && !exit);
        if (!exit)
            returnAnswer();
    }

    private String enterPosition() {
        display("Enter a position [1A-9I]:");
        return input.next();
    }

    private String enterNumber() {
        display("Press 0 to back to Menu");
        display("Press H to get a hint");
        display("Enter a number [1-9]:");
        return input.next();
    }

    /**
     * Return an answer according to the result of the game
     */
    private void returnAnswer() {
        if (this.board.isEqualsTo(this.solved))
            display("Congratulations you win !!\n");
        else
            display("You lose !!\n");
    }

    /**
     * Display the game board by console
     */
    private void displayGame() {
        String emptyCellChar = propertiesReader.getProperty("CHARACTER");
        String boardChars = this.board.parseToChars(emptyCellChar);
        display(boardChars);
    }

    /**
     * Write a number on the game board according to an specific position
     *
     * @param pos The position from 1A to 9I
     * @param num The number for that position
     */
    public void writeOnBoard(String pos, int num) {
        int row = Character.getNumericValue(pos.charAt(0)) - 1;
        int col = LETTERS.get(Character.toString(pos.charAt(1)));
        this.board.getCell(row, col).setValue(num);
    }

    /**
     * Get a hint according to an specific position
     *
     * @param pos The position from 1A to 9I
     * @return The solution number
     */
    public int getHint(String pos) {
        int row = Character.getNumericValue(pos.charAt(0)) - 1;
        int col = LETTERS.get(Character.toString(pos.charAt(1)));
        return this.solved.getCell(row, col).getValue();
    }
}
