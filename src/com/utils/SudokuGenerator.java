package com.utils;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.sudoku.Cell;
import com.sudoku.SudokuBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represent the Sudoku Generator class
 */
public class SudokuGenerator {

    private static SudokuBoard board = new SudokuBoard();

    public enum Level {
        EASY,
        MEDIUM,
        HARD
    }

    public static Map<Level, Integer> levels = new HashMap<Level, Integer>();

    static {
        levels.put(Level.EASY, 3);   // max 3 empty cells per sub grid
        levels.put(Level.MEDIUM, 5); // max 5 empty cells per sub grid
        levels.put(Level.HARD, 7);   // max 7 empty cells per sub grid
    }

    public static SudokuBoard generateEasy() {
        return generate(levels.get(Level.EASY));
    }

    public static SudokuBoard generateMedium() {
        return generate(levels.get(Level.MEDIUM));
    }

    public static SudokuBoard generateHard() {
        return generate(levels.get(Level.HARD));
    }

    private static SudokuBoard generate(Level level) {
        return generate(levels.get(level));
    }

    /**
     * Generate a board according to a complexity
     *
     * @param complexity The max empty cells per subGrid
     * @return The sudoku board generated
     */
    public static SudokuBoard generate(int complexity) {
        setOneCellOnSubGrids();

        Algorithm backtracking = new Backtracking();
        backtracking.solve(board);

        clearNCellsOnSubGrids(complexity);
        return board;
    }

    /**
     * Given there is nine sub grids on the board
     * numbered from 0 to 8, this method set one cell on each sub grid
     * randomly
     */
    public static void setOneCellOnSubGrids() {
        ArrayList<Integer> subGridOrder = getSubGridsOrder();
        for (Integer subGrid : subGridOrder) {
            setOneCellOnSubGrid(subGrid);
        }
    }

    /**
     * Given there is nine sub grids on the board
     * numbered from 0 to 8, this method clear n cells on each sub grid
     * randomly
     */
    public static void clearNCellsOnSubGrids(int complexity) {
        ArrayList<Integer> subGridOrder = getSubGridsOrder();
        for (Integer subGrid : subGridOrder) {
            clearNCellsOnSubGrid(subGrid, getComplexity(complexity));
        }
    }

    /**
     * Given there is nine cells on a sub grid
     * numbered from 0 to 8, this method gives a group of cell numbers
     * from sub grid according to the size parameter randomly
     *
     * @return The cell number randomly
     */
    private static int getComplexity(int maxComplexity) {
        int minComplexity = maxComplexity - 2;
        return NumberGenerator.generate(minComplexity, maxComplexity);
    }


    /**
     * Given there is nine sub grids on the board
     * numbered from 0 to 8, this method sorts the order randomly
     * to start filling them
     *
     * @return The random order to start filling the sub grids
     */
    private static ArrayList<Integer> getSubGridsOrder() {
        int iniSubGrid = 0;
        int endSubGrid = 8;
        int totalSubGrids = 9;
        return NumberGenerator.generate(iniSubGrid, endSubGrid, totalSubGrids);
    }

    /**
     * Set one cell on a sub grid
     *
     * @param subGridNumber The subGrid number on the board [0 ... 8]
     */
    public static void setOneCellOnSubGrid(int subGridNumber) {
        List<Cell> subGridCells = board.getSubGridCells(subGridNumber);

        int cellNumber = getSubGridCellNumber();
        int minSetters = 0;

        Cell cell = subGridCells.get(cellNumber);
        Integer value = NumberGenerator.getADigit();

        if (board.isSafeSetCell(cell, value)) {
            board.setCell(cell, value);
            minSetters++;
        }

        if (minSetters == 0) {
            setOneCellOnSubGrid(subGridNumber);
        }
    }

    /**
     * Clear n cells on a sub grid
     *
     * @param cellsNumber   The number of zeros
     * @param subGridNumber The subGrid number on the board [0 ... 8]
     */
    public static void clearNCellsOnSubGrid(int subGridNumber, int cellsNumber) {
        ArrayList<Integer> cellNumbers = getSubGridCells(cellsNumber);
        List<Cell> subGridCells = board.getSubGridCells(subGridNumber);
        for (Integer cellNumber : cellNumbers) {
            Cell cell = subGridCells.get(cellNumber);
            board.clearCell(cell);
        }
    }

    /**
     * Given there is nine cells on a sub grid
     * numbered from 0 to 8, this method gives a group of cell numbers
     * from sub grid according to the size parameter randomly
     *
     * @param size The number of cells to be return
     * @return The group of cell numbers
     */
    private static ArrayList<Integer> getSubGridCells(int size) {
        int iniCell = 0;
        int endCell = 8;
        return NumberGenerator.generate(iniCell, endCell, size);
    }

    /**
     * Given there is nine cells on a sub grid
     * numbered from 0 to 8, this method gives a group of cell numbers
     * from sub grid according to the size parameter randomly
     *
     * @return The cell number randomly
     */
    private static int getSubGridCellNumber() {
        int iniCell = 0;
        int endCell = 8;
        return NumberGenerator.generate(iniCell, endCell);
    }
}
