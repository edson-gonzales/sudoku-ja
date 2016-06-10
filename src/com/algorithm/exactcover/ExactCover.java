package com.algorithm.exactcover;

import com.algorithm.exactcover.nodes.DancingNode;
import com.sudoku.SudokuBoard;

import java.util.Arrays;
import java.util.List;

/**
 * This class represent the exact cover algorithm
 */
public class ExactCover {
    private SudokuBoard sudokuBoard;
    private int size = SudokuBoard.SIZE;
    private int side = 3;
    private int number;
    int hBase = 0;
    private int[][] SudokuExactCover = new int[size * size * size][size * size * 4];

    public ExactCover() {
        size = SudokuBoard.SIZE;
    }

    /**
     * @param sudoku
     * @return
     */
    private int[][] makeExactCoverGrid(int[][] sudoku) {
        int[][] ExactCoverBoard = getSudokuExactCover();
        for (int rows = 1; rows <= size; rows++) {
            for (int columns = 1; columns <= size; columns++) {
                number = sudoku[rows - 1][columns - 1];
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
     * Returns the base exact cover grid for a SUDOKU puzzle
     *
     * @return
     */
    private int[][] getSudokuExactCover() {

        // row-column constraints
        for (int row = 1; row <= size; row++) {
            iterateRowColumnConstraints(row);
        }

        // row-number constraints
        for (int row = 1; row <= size; row++) {
            iterateRowNumberConstraints(row);
        }

        // column-number constraints
        for (int column = 1; column <= size; column++) {
            iterateColumnNumberConstraints(column);
        }

        // box-number constraints
        for (int boxRow = 1; boxRow <= size; boxRow += side) {
            for (int boxColumn = 1; boxColumn <= size; boxColumn += side) {
                iterateBoxNumberConstraints(boxRow, boxColumn);
            }
        }
        return SudokuExactCover;
    }

    private void iterateRowColumnConstraints(int row){
        for (int column = 1; column <= size; column++, hBase++) {
            for (int number = 1; number <= size; number++) {
                SudokuExactCover[getIndex(row, column, number)][hBase] = 1;
            }
        }
    }

    private void iterateRowNumberConstraints(int row){
        for (int number = 1; number <= size; number++, hBase++) {
            for (int column = 1; column <= size; column++) {
                SudokuExactCover[getIndex(row, column, number)][hBase] = 1;
            }
        }
    }

    private void iterateColumnNumberConstraints(int column){
        for (int number = 1; number <= size; number++, hBase++) {
            for (int row = 1; row <= size; row++) {
                SudokuExactCover[getIndex(row, column, number)][hBase] = 1;
            }
        }
    }

    private void iterateBoxNumberConstraints(int boxRow, int boxColumn){
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
     * @param sudoku The sudoku board of cells
     * @return The condition of game result
     */
    protected boolean solve(int[][] sudoku) {
        int[][] cover = makeExactCoverGrid(sudoku);
        DancingLinks dlx = new DancingLinks(cover, new ExactCover());
        this.sudokuBoard = dlx.runSolver();
        if(!this.sudokuBoard.hasAnEmptyCell()){
            return true;
        }
        return false;
    }

    /**
     * Parse the board
     *
     * @param answer
     * @return
     */
    private int[][] parseBoard(List<DancingNode> answer) {
        int[][] result = new int[size][size];
        for (DancingNode actualNode : answer) {
            DancingNode rcNode = actualNode;
            int min = Integer.parseInt(rcNode.getC().getName());
            for (DancingNode temp = actualNode.getRight(); temp != actualNode; temp = temp.getRight()) {
                int val = Integer.parseInt(temp.getC().getName());
                if (val < min) {
                    min = val;
                    rcNode = temp;
                }
            }
            int ans1 = Integer.parseInt(rcNode.getC().getName());
            int ans2 = Integer.parseInt(rcNode.getRight().getC().getName());
            int r = ans1 / size;
            int c = ans1 % size;
            int num = (ans2 % size) + 1;
            result[r][c] = num;
        }
        return result;
    }

    public SudokuBoard handleSolution(List<DancingNode> answer) {
        int[][] result = parseBoard(answer);
        SudokuBoard sudokuBoard = new SudokuBoard(result);
        return sudokuBoard;
    }

    public static void main(String[] args) {
        int[][] covers = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
        ExactCover exactCover = new ExactCover();
        SudokuBoard sudokuBoard = new SudokuBoard(covers);
        System.out.println(exactCover.solve(covers));
        System.out.println(sudokuBoard.toString());
    }
}
