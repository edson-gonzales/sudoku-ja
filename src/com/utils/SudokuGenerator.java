package com.utils;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.sudoku.Cell;
import com.sudoku.SudokuBoard;
import com.utils.writers.WriterManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent the Sudoku Generator class
 */
public class SudokuGenerator {

    private static SudokuBoard board = new SudokuBoard();

    /**
     * Generate a board according to a complexity range
     *
     * @param minComplexity The min empty cells per subGrid
     * @param maxComplexity The max empty cells per subGrid
     * @return The sudoku board generated
     */
    public static void generate(int minComplexity, int maxComplexity) {
        setOneCellOnSubGrids();

        Algorithm backtracking = new Backtracking();

        if (!backtracking.solve(board))
            generate(minComplexity, maxComplexity);

        clearNCellsOnSubGrids(minComplexity, maxComplexity);

        exportSudokuGame();
    }

    /**
     * Given there are nine sub grids on the board
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
     * Given there are nine sub grids on the board
     * numbered from 0 to 8, this method clear n cells on each sub grid
     * randomly
     *
     * @param minComplexity The min empty cells per subGrid
     * @param maxComplexity The max empty cells per subGrid
     */
    public static void clearNCellsOnSubGrids(int minComplexity, int maxComplexity) {
        ArrayList<Integer> subGridOrder = getSubGridsOrder();
        for (Integer subGrid : subGridOrder) {
            clearNCellsOnSubGrid(subGrid, getComplexity(minComplexity, maxComplexity));
        }
    }

    /**
     * Get a complexity between a range
     * where the minimum is calculated 2 cells minus from the maximum
     *
     * @param minComplexity The min empty cells per subGrid
     * @param maxComplexity The max empty cells per subGrid
     * @return The cell number randomly
     */
    private static int getComplexity(int minComplexity, int maxComplexity) {
        return NumberGenerator.generate(minComplexity, maxComplexity);
    }

    /**
     * Given there are nine sub grids on the board
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

        Cell cell = subGridCells.get(cellNumber);
        Integer value = NumberGenerator.getADigit();

        if (board.isSafeSetCell(cell, value))
            board.setCell(cell, value);

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
     * Given there are nine cells on a sub grid
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
     * Given there are nine cells on a sub grid
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

    /**
     * Export sudoku game generated to an txt file
     */
    private static void exportSudokuGame() {
        try {
            WriterManager.exportTxtFile(board.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
