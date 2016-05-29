package com.algorithm;

import com.sudoku.Cell;
import com.sudoku.SudokuBoard;

/**
 * This class represent the Backtracking algorithm
 */
public class Backtracking implements Algorithm {
    private SudokuBoard board;

    /**
     * Solve a sudoku board game
     *
     * @param sudokuBoard The sudoku board of cells
     * @return The condition of game result
     */
    @Override
    public Boolean solve(SudokuBoard sudokuBoard) {
        this.board = sudokuBoard;

        if (!this.board.hasAnEmptyCell()) {
            return true;
        }

        Cell cell = this.board.getFirstEmptyCell();

        for (int num = 1; num <= 9; num++) {
            if (isNumSetAsSolution(cell, num))
                return true;
        }
        return false;
    }

    /**
     * Verify if a num is set as solution on a cell
     *
     * @param cell A Cell object on the board
     * @param num  A digit number
     * @return Return true if there is a solution
     */
    private boolean isNumSetAsSolution(Cell cell, int num) {
        if (isPracticable(cell, num)) {
            this.board.setCell(cell, num);
            if (solve(this.board)) {
                return true;
            }
            this.board.clearCell(cell);
        }
        return false;
    }

    /**
     * Verify if a num is practicable to set in a cell on the board
     *
     * @param cell A Cell object on the board
     * @param num    A digit number
     * @return Return true if the num is practicable in row, column and sub grid
     */
    public Boolean isPracticable(Cell cell, int num) {
        Boolean isPracticableInColumn = !this.board.isNumUsedInColumnCell(cell, num);
        Boolean isPracticableInRow = !this.board.isNumUsedInCellRow(cell, num);
        Boolean isPracticableInSubGrid = !this.board.isNumUsedInSubGrid(cell, num);
        return isPracticableInColumn && isPracticableInRow && isPracticableInSubGrid;
    }
}
