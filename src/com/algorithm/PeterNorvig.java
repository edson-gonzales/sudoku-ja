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
    private static final int SIZE = 9;
    @Override
    public Boolean solve(SudokuBoard sudokuBoard) {
        return findSolution(sudokuBoard, 0);
    }

    private Boolean findSolution(SudokuBoard sudokuBoard, int index) {
        int row = index/SIZE, column = index%SIZE;

        if (index == SIZE*SIZE) return true;
        if (!sudokuBoard.getCell(row, column).isCellEmpty())  return findSolution(sudokuBoard, index+1);

        for (Integer value : getDigitsForCell(row, column, sudokuBoard)) {
            sudokuBoard.getCell(row, column).setValue(value);
            if (findSolution(sudokuBoard, index+1)) return true;
        }
        sudokuBoard.setCell(row, column, 0);
        return false;
    }



    public List<Integer> getDigitsForCell(int row, int column, SudokuBoard sudokuBoard) {
        List<Integer> digits = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

            digits = deleteDigitsInRow(digits, row, sudokuBoard);
            digits = deleteDigitsInColumn(digits, column, sudokuBoard);
            digits = deleteDigitsInSubGrid(digits, row, column, sudokuBoard);

//        deleteDigitsInRow(digits, row, sudokuBoard);
//        deleteDigitsInColumn(digits, column, sudokuBoard);
//        deleteDigitsInSubGrid(digits, row, column, sudokuBoard);
        return digits;
    }

    private List<Integer> deleteDigitsInSubGrid(List<Integer> digits, int row, int column, SudokuBoard sudokuBoard) {
        int initPosX = sudokuBoard.getIniPosSubGrid(row);
        int initPosY = sudokuBoard.getIniPosSubGrid(column);
        for (int i = initPosX; i < initPosX + 3; i++) {
            for (int j = initPosY; j < initPosY + 3; j++) {
                if (!sudokuBoard.getCell(row,column).isCellEmpty()){
                    digits.remove(digits.indexOf(sudokuBoard.getCell(row, column).getValue()));
                }
            }
        }
        return digits;
    }

    private List<Integer> deleteDigitsInColumn(List<Integer> digits, int column, SudokuBoard sudokuBoard) {
        for (int row = 0; row < sudokuBoard.getBoardSize(); row++)
            if (!sudokuBoard.getCell(row,column).isCellEmpty()){
                digits.remove(digits.indexOf(sudokuBoard.getCell(row, column).getValue()));
            }

        return digits;
    }


    private List<Integer> deleteDigitsInRow(List<Integer> digits, int row, SudokuBoard sudokuBoard) {
        for (int column = 0; column < SIZE; column++)
            if (!sudokuBoard.getCell(row,column).isCellEmpty()){
                digits.remove(digits.indexOf(sudokuBoard.getCell(row, column).getValue()));
            }
        return digits;
    }

    public static void main(String[] args) {
        List<Integer> digits = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println(digits);
        digits.remove(digits.indexOf(0));
        System.out.println(digits);
    }
}
