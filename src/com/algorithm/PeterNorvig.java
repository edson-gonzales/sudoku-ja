package com.algorithm;

import com.sudoku.Cell;
import com.sudoku.SudokuBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represent the Peter Norvig's algorithm
 */
public class PeterNorvig implements Algorithm {
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

        for (Integer num : getNumbersForCell(cell)) {
            this.board.setCell(cell, num);
            if (solve(this.board))
                return true;
        }

        this.board.clearCell(cell);

        return false;
    }

    /**
     * Get the possible numbers to set in a cell on the board
     *
     * @param cell A Cell object on the board
     * @return Return a list of the possible numbers to be set
     */
    public List<Integer> getNumbersForCell(Cell cell) {
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        numbers = deleteNumbersInRow(numbers, cell);
        numbers = deleteNumbersInColumn(numbers, cell);
        numbers = deleteNumbersInSubGrid(numbers, cell);
        return numbers;
    }

    /**
     * Delete the numbers that can not be set in a cell on the board
     * according to the numbers on its sub grid
     *
     * @param numbers The list of numbers
     * @param cell    A Cell object on the board
     * @return Return a list of numbers that are not in the sub grid
     */
    private List<Integer> deleteNumbersInSubGrid(List<Integer> numbers, Cell cell) {
        Cell iniCell = this.board.getIniCellSubGrid(cell);
        int initPosX = iniCell.getPosX();
        int initPosY = iniCell.getPosY();
        for (int row = initPosX; row < initPosX + 3; row++) {
            for (int column = initPosY; column < initPosY + 3; column++) {
                deleteCellValue(numbers, this.board.getCell(row, column));
            }
        }
        return numbers;
    }

    /**
     * Delete a number on the list according to the value of a cell
     *
     * @param numbers The list of numbers
     * @param cell    A Cell object on the board
     */
    private void deleteCellValue(List<Integer> numbers, Cell cell) {
        Integer num = cell.getValue();
        if (!cell.isEmpty() && numbers.contains(num)) {
            numbers.remove(numbers.indexOf(num));
        }
    }

    /**
     * Delete the numbers that can not be set in a cell on the board
     * according to the numbers on its column
     *
     * @param numbers The list of numbers
     * @param cell    A Cell object on the board
     * @return Return a list of numbers that are not in the sub grid
     */
    private List<Integer> deleteNumbersInColumn(List<Integer> numbers, Cell cell) {
        int column = cell.getPosY();
        for (int row = 0; row < SudokuBoard.SIZE; row++)
            deleteCellValue(numbers, this.board.getCell(row, column));
        return numbers;
    }

    /**
     * Delete the numbers that can not be set in a cell on the board
     * according to the numbers on its row
     *
     * @param numbers The list of numbers
     * @param cell    A Cell object on the board
     * @return Return a list of numbers that are not in the row
     */
    private List<Integer> deleteNumbersInRow(List<Integer> numbers, Cell cell) {
        Integer row = cell.getPosX();
        for (int column = 0; column < SudokuBoard.SIZE; column++)
            deleteCellValue(numbers, this.board.getCell(row, column));
        return numbers;
    }
}
