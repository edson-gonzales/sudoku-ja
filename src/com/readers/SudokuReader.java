package com.readers;

import com.sudoku.SudokuBoard;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.log4j.Logger;


/**
 * Class that contains readers methods to read Sudokus from files
 *
 * @author Jose Cabrera
 */
public class SudokuReader {
    protected String filePath;
    protected Scanner scanner = null;
    public static final int ASCCI_ZERO = "0".charAt(0);
    private static int BOARD_SIZE = SudokuBoard.SIZE;
    private File file;
    public SudokuReader(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Method to return a SudokuBoard
     *
     * @return SudokuBoard is a sudokuBoard that contains the data obtained from the file
     */
    public SudokuBoard getSudokuBoard() {
        String parsedText = read().replace(",", "");
        int iterator = 0;
        int[][] grid = new int[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                grid[row][col] = parsedText.charAt(iterator) - ASCCI_ZERO;
                iterator++;
            }
        }
        return new SudokuBoard(grid);
    }

    /**
     * Read a file
     *
     * @return text obtained from file
     */
    private String read() {
        String text = "";
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                text = text + scanner.next();
            }
        } catch (IOException ioException) {
            Logger.getLogger(SudokuReader.class).error("Unable to read file", ioException);
        } catch (NullPointerException nullPointerException) {
            Logger.getLogger(SudokuReader.class).error("The file is null", nullPointerException);
        } finally {
            scanner.close();
        }
        return text;
    }
}
