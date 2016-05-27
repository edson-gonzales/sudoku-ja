package com.algorithm;

import com.sudoku.Cell;
import com.sudoku.SudokuBoard;


/**
 * This class represent the Backtracking algorithm
 */
public class Backtracking implements Algorithm {

    @Override
    public Boolean solve(SudokuBoard sudokuBoard) {

        Cell emptyCell = sudokuBoard.getFirstEmptyCell();

        if (emptyCell == null) {
            return true;
        }

        int i = emptyCell.getPosX();
        int j = emptyCell.getPosY();

        for (int num = 1; num <= 9; num++) {
            if (isPracticable(i, j, sudokuBoard, num)) {
                sudokuBoard.setCell(i, j, num);
                if (solve(sudokuBoard)) {
                    return true;
                }
                sudokuBoard.setCell(i, j, 0);
            }
        }
        return false;
    }

    public Boolean isPracticable(int i, int j, SudokuBoard sudokuBoard, int num) {
        Boolean isPracticableInColumn = !sudokuBoard.isUsedInColumn(j, num);
        Boolean isPracticableInRow = !sudokuBoard.isUsedInRow(i, num);
        Boolean isPracticableInSubGrid = !sudokuBoard.isUsedInSubGrid(i, j, num);
        return isPracticableInColumn && isPracticableInRow && isPracticableInSubGrid;
    }
}
