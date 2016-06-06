package com.sudoku;

import com.utils.SudokuGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Alejandra on 04/06/2016.
 */
public class Game {
    private SudokuBoard board;
    private SudokuGenerator sudokuGenerator;
    private static final Map<String, Integer> LETTERS = new HashMap<String, Integer>();

    {
        LETTERS.put("A", 0);
        LETTERS.put("B", 1);
        LETTERS.put("C", 2);
        LETTERS.put("D", 3);
        LETTERS.put("E", 4);
        LETTERS.put("F", 5);
        LETTERS.put("G", 6);
        LETTERS.put("H", 7);
        LETTERS.put("I", 8);
    }

    public Game() {
        sudokuGenerator = new SudokuGenerator();
    }

    public void start() {
        System.out.println("--------Sudoku Game-------" + "\n");
        board = sudokuGenerator.generate(1, 1);
        System.out.println(board.toString());
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Press 0 to back to the menu");
            System.out.println("Enter a position [1A-9I]: ");
            String pos = input.next();
            if (Character.getNumericValue(pos.charAt(0)) == 0)
                break;
            System.out.println("Enter a number [1-9]: ");
            int num = input.nextInt();
            if (num == 0)
                break;
            writeOnAnCell(pos, num);
            System.out.println(board.toString());
        } while (board.hasAnEmptyCell());
    }

    public void writeOnAnCell(String pos, int num) {
        int row = Character.getNumericValue(pos.charAt(0)) - 1;
        int col = LETTERS.get(Character.toString(pos.charAt(1)));
        board.getCell(row, col).setValue(num);
    }
}
