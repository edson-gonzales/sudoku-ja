package com.sudoku;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.algorithm.PeterNorvig;

public class Main {

    public static void main(String[] args) {

        int grid[][] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        SudokuBoard sudokuBoard = new SudokuBoard(grid);
//        Algorithm backtracking = new Backtracking();
//        Boolean isSolved = backtracking.solve(sudokuBoard);
//
//        if (isSolved)
//            System.out.println(sudokuBoard.toString());
//        else
//            System.out.println(("There is not solution"));

        Algorithm peterNorvig = new PeterNorvig();
        Boolean isSolved = peterNorvig.solve(sudokuBoard);

        if (isSolved)
            System.out.println(sudokuBoard.toString());
        else
            System.out.println(("There is not solution"));
    }
}
