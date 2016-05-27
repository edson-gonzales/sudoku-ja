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
    private SudokuBoard sudokuBoard;

    /**
     * Solve a sudoku sudokuBoard game
     *
     * @param sudokuBoard The sudoku sudokuBoard of cells
     * @return The condition of game result
     */
    @Override
    public Boolean solve(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;

        Cell emptyCell = this.sudokuBoard.getFirstEmptyCell();

        if (this.sudokuBoard.getFirstEmptyCell() == null) {
            return true;
        }

        int row = emptyCell.getPosX();
        int column = emptyCell.getPosY();

        for (Integer num : getNumbersForCell(row, column)) {
            this.sudokuBoard.getCell(row, column).setValue(num);
            if (solve(this.sudokuBoard))
                return true;
        }

        this.sudokuBoard.clearCell(row, column);

        return false;
    }

    /**
     * Get the possible numbers to set in a cell on the board
     *
     * @param row    The row position in the board
     * @param column The column position in the board
     * @return Return a list of the possible numbers to be set
     */
    public List<Integer> getNumbersForCell(int row, int column) {
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        numbers = deleteNumbersInRow(numbers, row);
        numbers = deleteNumbersInColumn(numbers, column);
        numbers = deleteNumbersInSubGrid(numbers, row, column);
        return numbers;
    }

    /**
     * Delete the numbers that can not be set in a cell on the board
     * according to the numbers on its sub grid
     *
     * @param numbers The list of numbers
     * @param row     The row position in the board
     * @param column  The column position in the board
     * @return Return a list of numbers that are not in the sub grid
     */
    private List<Integer> deleteNumbersInSubGrid(List<Integer> numbers, int row, int column) {
        int initPosX = SudokuBoard.getIniPosSubGrid(row);
        int initPosY = SudokuBoard.getIniPosSubGrid(column);
        for (int i = initPosX; i < initPosX + 3; i++) {
            for (int j = initPosY; j < initPosY + 3; j++) {
                deleteCellValue(numbers, this.sudokuBoard.getCell(row, column));
            }
        }
        return numbers;
    }

    /**
     * Delete a number on the list according to the value of a cell
     *
     * @param numbers The list of numbers
     * @param cell    A Cell on the board
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
     * @param column  The column position in the board
     * @return Return a list of numbers that are not in the sub grid
     */
    private List<Integer> deleteNumbersInColumn(List<Integer> numbers, int column) {
        for (int row = 0; row < this.sudokuBoard.getBoardSize(); row++)
            deleteCellValue(numbers, this.sudokuBoard.getCell(row, column));
        return numbers;
    }

    /**
     * Delete the numbers that can not be set in a cell on the board
     * according to the numbers on its row
     *
     * @param numbers The list of numbers
     * @param row     The row position in the board
     * @return Return a list of numbers that are not in the row
     */
    private List<Integer> deleteNumbersInRow(List<Integer> numbers, int row) {
        for (int column = 0; column < this.sudokuBoard.getBoardSize(); column++)
            deleteCellValue(numbers, this.sudokuBoard.getCell(row, column));
        return numbers;
    }
}
