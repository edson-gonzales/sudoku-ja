package com.algorithm;

import com.sudoku.SudokuBoard;

/**
 * This class represent the Algorithm interface
 */
public interface Algorithm {

    /**
     * Solve a sudoku board game
     *
     * @param sudokuBoard The sudoku board of cells
     * @return The condition of game result
     */
    public Boolean solve(SudokuBoard sudokuBoard);
}
