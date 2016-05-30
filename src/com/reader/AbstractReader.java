package com.reader;

import com.sudoku.SudokuBoard;

import java.util.Scanner;

/**
 * Class that contains reader methods to read different types of Files
 */
public abstract class AbstractReader {
    protected String filePath;
    protected Scanner scanner = null;
    public static final int ASCCI_ZERO = "0".charAt(0);

    public AbstractReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to get the content of file
     *
     * @return content
     */
    public abstract String getContentFile();

    /**
     * Method to return a SudokuBoard
     *
     * @return SudokuBoard
     */
    public SudokuBoard getSudokuBoard() {
        String parsedText = getContentFile();
        int iterator = 0;
        int[][] grid = new int[9][9];
        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                grid[col][row] = parsedText.charAt(iterator) - ASCCI_ZERO;
                iterator++;
            }
        }
        return new SudokuBoard(grid);
    }
}
