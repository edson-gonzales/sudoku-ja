package com.algorithm.exactcover;

import com.algorithm.Algorithm;
import com.sudoku.SudokuBoard;

import java.util.Arrays;

/**
 * This class represent the exact cover algorithm
 *
 * @author Jose Cabrera
 */
public class ExactCover implements Algorithm {
    public static SudokuBoard board;
    private int size = SudokuBoard.SIZE;
    private int side = 3;
    private int number;
    int hBase = 0;
    private int[][] SudokuExactCover = new int[size * size * size][size * size * 4];

    /**
     * Find a set or more of rows in which exactly one 1 will appear for each column.
     *
     * @return a grid with exact cover
     */
    private int[][] makeExactCoverGrid() {
        int[][] ExactCoverBoard = getSudokuExactCover();
        for (int rows = 1; rows <= size; rows++) {
            for (int columns = 1; columns <= size; columns++) {
                number = board.getCell(rows - 1, columns - 1).getValue();
                verifyZerosInGrid(ExactCoverBoard, rows, columns, number);
            }
        }
        return ExactCoverBoard;
    }

    /**
     * Verify if there is an empty cell in the board
     *
     * @return The condition of the board if it contains an empty cell
     */
    public void verifyZerosInGrid(int[][] board, int row, int column, int number) {
        if (number != 0) {
            for (int num = 1; num <= size; num++) {
                if (num != number) {
                    Arrays.fill(board[getIndex(row, column, num)], 0);
                }
            }
        }
    }

    /**
     * Iterate in different ways to obtain a exact cover grid for a sudoku puzzle
     *
     * @return sudoku Exact Cover
     */
    private int[][] getSudokuExactCover() {
        for (int row = 1; row <= size; row++) {
            iterateRowColumnConstraints(row);
        }

        for (int row = 1; row <= size; row++) {
            iterateRowNumberConstraints(row);
        }

        for (int column = 1; column <= size; column++) {
            iterateColumnNumberConstraints(column);
        }

        for (int boxRow = 1; boxRow <= size; boxRow += side) {
            for (int boxColumn = 1; boxColumn <= size; boxColumn += side) {
                iterateBoxNumberConstraints(boxRow, boxColumn);
            }
        }
        return SudokuExactCover;
    }

    /**
     * Iterate the rows to satisfy the Constraints
     *
     * @param row the row that will be iterated
     */
    private void iterateRowColumnConstraints(int row) {
        for (int column = 1; column <= size; column++, hBase++) {
            for (int number = 1; number <= size; number++) {
                SudokuExactCover[getIndex(row, column, number)][hBase] = 1;
            }
        }
    }

    /**
     * Iterate the rows to satisfy the Constraints
     *
     * @param row the row that will be iterated
     */
    private void iterateRowNumberConstraints(int row) {
        for (int number = 1; number <= size; number++, hBase++) {
            for (int column = 1; column <= size; column++) {
                SudokuExactCover[getIndex(row, column, number)][hBase] = 1;
            }
        }
    }

    /**
     * Iterate the columns to satisfy the Constraints
     *
     * @param column the column that will be iterated
     */
    private void iterateColumnNumberConstraints(int column) {
        for (int number = 1; number <= size; number++, hBase++) {
            for (int row = 1; row <= size; row++) {
                SudokuExactCover[getIndex(row, column, number)][hBase] = 1;
            }
        }
    }

    /**
     * Iterate the box to belong the number to satisfy the Constraints
     *
     * @param boxRow    the boxRow that will be iterated
     * @param boxColumn the boxColumn that will be iterated
     */
    private void iterateBoxNumberConstraints(int boxRow, int boxColumn) {
        for (int number = 1; number <= size; number++, hBase++) {
            for (int rDelta = 0; rDelta < side; rDelta++) {
                for (int cDelta = 0; cDelta < side; cDelta++) {
                    SudokuExactCover[getIndex(boxRow + rDelta, boxColumn + cDelta, number)][hBase] = 1;
                }
            }
        }
    }

    /**
     * @param row represent the row [1,size] of SudokuBoard
     * @param col represent the col [1,size] of SudokuBoard
     * @param num represent the num [1,size] of SudokuBoard
     * @return the index
     */
    private int getIndex(int row, int col, int num) {
        return (row - 1) * size * size + (col - 1) * size + (num - 1);
    }

    /**
     * Solve a sudoku board game
     *
     * @param sudokuBoard The sudoku board of cells
     * @return The condition of game result
     */
    @Override
    public Boolean solve(SudokuBoard sudokuBoard) {
        this.board = sudokuBoard;
        int[][] cover = makeExactCoverGrid();
        DancingLinks dlx = new DancingLinks(cover);
        this.board = dlx.searchSolution();
        if (!this.board.hasAnEmptyCell()) {
            return true;
        }
        return false;
    }
}
