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

        int row = emptyCell.getPosX();
        int column = emptyCell.getPosY();

        for (int num = 1; num <= 9; num++) {
            if (isPracticable(row, column, sudokuBoard, num)) {
                sudokuBoard.setCell(row, column, num);
                if (solve(sudokuBoard)) {
                    return true;
                }
                sudokuBoard.clearCell(row, column);
            }
        }
        return false;
    }

    public Boolean isPracticable(int row, int column, SudokuBoard sudokuBoard, int num) {
        Boolean isPracticableInColumn = !sudokuBoard.isUsedInColumn(column, num);
        Boolean isPracticableInRow = !sudokuBoard.isUsedInRow(row, num);
        Boolean isPracticableInSubGrid = !sudokuBoard.isUsedInSubGrid(row, column, num);
        return isPracticableInColumn && isPracticableInRow && isPracticableInSubGrid;
    }
}
