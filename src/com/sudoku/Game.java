package com.sudoku;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.utils.SudokuGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Alejandra on 04/06/2016.
 */
public class Game {
    private String emptyCellChar = "*";
    private int minComplexity = 1;
    private int maxComplexity = 5;
    private Algorithm algorithm;
    private SudokuBoard board;
    private SudokuBoard solve;
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
        this.board = SudokuGenerator.generate(this.minComplexity, this.maxComplexity);
        this.solve = new SudokuBoard(this.board.getArrayBoard());
        this.algorithm = new Backtracking();
        algorithm.solve(this.solve);
    }

    public void start() {
        System.out.println("--------Sudoku Game-------" + "\n");
        System.out.println(this.board.parseToChars(emptyCellChar));
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Press 0 to back to Menu");
            System.out.println("Press H to get a hint");
            System.out.println("Enter a position [1A-9I]:");
            String pos = input.next();
            if (Character.getNumericValue(pos.charAt(0)) == 0)
                break;
            System.out.println("Enter a number [1-9]:");
            String number = input.next();

            if (number.equals("H")) {
                writeOnAnCell(pos, getHint(pos));
            } else if (number.equals("0"))
                break;
            else {
                int num = Integer.parseInt(number);
                writeOnAnCell(pos, num);
            }
            System.out.println(this.board.parseToChars(emptyCellChar));
        } while (this.board.hasAnEmptyCell());
    }

    public void writeOnAnCell(String pos, int num) {
        int row = Character.getNumericValue(pos.charAt(0)) - 1;
        int col = LETTERS.get(Character.toString(pos.charAt(1)));
        this.board.getCell(row, col).setValue(num);
    }

    public int getHint(String pos) {
        int row = Character.getNumericValue(pos.charAt(0)) - 1;
        int col = LETTERS.get(Character.toString(pos.charAt(1)));
        algorithm.solve(this.solve);
        return this.solve.getCell(row, col).getValue();
    }
}
