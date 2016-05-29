package com.sudoku;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.algorithm.PeterNorvig;
import com.utils.WriterManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        int grid[][] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
//                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
//                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
//                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
//                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
//                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
//                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
//                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
//                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};
//
//        SudokuBoard sudokuBoard = new SudokuBoard(grid);
//        System.out.println(sudokuBoard.toString());
//        Algorithm backtracking = new Backtracking();
//        Boolean isSolved = backtracking.solve(sudokuBoard);
//
//        if (isSolved)
//            System.out.println(sudokuBoard.toString());
//        else
//            System.out.println(("There is not solution"));
//
//        Algorithm peterNorvig = new PeterNorvig();
//        isSolved = peterNorvig.solve(sudokuBoard);
//
//        if (isSolved)
//            System.out.println(sudokuBoard.toString());
//        else
//            System.out.println(("There is not solution"));
//
//        List<String> orders = new ArrayList<String>();
//        orders.add("1");
//        orders.add("2");
//        orders.add("3");
//        orders.add("4");
//        orders.add("5");
//        Integer size =  orders.size()- 1;
//        System.out.println(orders.get(size - 1 ));



//        3 1 6 5 7 8 4 9 2
//        5 2 9 1 3 4 7 6 8
//        4 8 7 6 2 9 5 3 1
//        2 6 3 4 1 5 9 8 7
//        9 7 4 8 6 3 1 2 5
//        8 5 1 7 9 2 6 4 3
//        1 3 8 9 4 7 2 5 6
//        6 9 2 3 5 1 8 7 4
//        7 4 5 2 8 6 3 1 9

        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.generateSudokuBoard(4);
        System.out.println(sudokuBoard.toString());
        Algorithm backtracking = new Backtracking();
        Boolean isSolved = backtracking.solve(sudokuBoard);

        if (isSolved)
            System.out.println(sudokuBoard.toString());
        else
            System.out.println(("Backtracking : There is not solution"));

        Algorithm peterNorvig = new PeterNorvig();
        isSolved = peterNorvig.solve(sudokuBoard);

        if (isSolved)
            System.out.println(sudokuBoard.toString());
        else
            System.out.println(("PeterNorvig : There is not solution"));

        WriterManager.exportTxtFile(sudokuBoard.toString());
//        try {
//            String str = sudokuBoard.toString();
//            File newTextFile = new File("./results/thetextfile.txt");
//
//            FileWriter fw = new FileWriter(newTextFile);
//            fw.write(str);
//            fw.close();
//
//        } catch (IOException iox) {
//            //do stuff with exception
//            iox.printStackTrace();
//        }
    }



}
