package com.algorithm;

import com.sudoku.Cell;
import com.sudoku.SudokuBoard;

/**
 * This class represent the Backtracking algorithm
 */
public class Backtracking implements Algorithm {
    private SudokuBoard sudokuBoard;

    /**
     * Solve a sudoku board game
     *
     * @param sudokuBoard The sudoku board of cells
     * @return The condition of game result
     */
    @Override
    public Boolean solve(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        Cell emptyCell = sudokuBoard.getFirstEmptyCell();

        if (emptyCell == null) {
            return true;
        }

        int row = emptyCell.getPosX();
        int column = emptyCell.getPosY();

        for (int num = 1; num <= 9; num++) {
            if (findSolution(row, column, num)) return true;
        }
        return false;
    }

    private boolean findSolution(int row, int column, int num) {
        if (isPracticable(row, column, num)) {
            sudokuBoard.setCell(row, column, num);
            if (solve(sudokuBoard)) {
                return true;
            }
            sudokuBoard.clearCell(row, column);
        }
        return false;
    }

    /**
     * Verify if a num is practicable to set in a cell on the board
     *
     * @param row    The row position in the board
     * @param column The column position in the board
     * @param num    A digit number
     * @return Return true if the num is practicable in row, column and sub grid
     */
    public Boolean isPracticable(int row, int column, int num) {
        Boolean isPracticableInColumn = !this.sudokuBoard.isUsedInColumn(column, num);
        Boolean isPracticableInRow = !this.sudokuBoard.isUsedInRow(row, num);
        Boolean isPracticableInSubGrid = !this.sudokuBoard.isUsedInSubGrid(row, column, num);
        return isPracticableInColumn && isPracticableInRow && isPracticableInSubGrid;
    }
}
