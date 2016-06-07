package com.gui;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.sudoku.SudokuBoard;
import com.utils.SudokuGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represent the Game console interface
 */
public class Game extends Console{
    private String emptyCellChar = ".";
    private int minComplexity = 0;
    private int maxComplexity = 1;
    private Algorithm algorithm = new Backtracking();
    private SudokuBoard board;
    private SudokuBoard solve;

    private static final Map<String, Integer> LETTERS = new HashMap<>();

    {
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
        this.board = SudokuGenerator.generate(this.minComplexity, this.maxComplexity);
        this.solve = new SudokuBoard(this.board.parseToArray());
        algorithm.solve(this.solve);

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
        if (this.board.isEqualsTo(this.solve))
            display("Congratulations you win !!\n");
        else
            display("You lose !!\n");
    }

    /**
     * Display the game board by console
     */
    private void displayGame() {
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
        this.algorithm.solve(this.solve);
        return this.solve.getCell(row, col).getValue();
    }
}
