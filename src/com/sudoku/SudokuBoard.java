package com.sudoku;

import com.algorithm.Algorithm;
import com.algorithm.Backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class represent the Sudoku board
 *
 * @author Alejandra Arteaga
 * @author Jose Cabrera
 */
public class SudokuBoard {
    private static int BOARD_SIZE = 9;

    private static int EASY_LEVEL = 5;
    private static int MEDIUM_LEVEL = 4;
    private static int HARD_LEVEL = 2;

    private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

    public SudokuBoard(int[][] arrayBoard) {
        parseToCells(arrayBoard);
    }

    public SudokuBoard(Cell[][] board) {
        this.board = board;
    }

    public SudokuBoard() {
        parseToEmptyCells();
    }

    public void generateSudokuBoard(int complexity) {
        parseToEmptyCells();
        ArrayList<Integer> subGridOrder = generateNumbersWithoutRepetitions(0, 8, 9);

        for (Integer subGrid : subGridOrder) {
            fillSubGridWithDigits(subGrid, 1);
        }

        Algorithm backtracking = new Backtracking();
        backtracking.solve(this);

        subGridOrder = generateNumbersWithoutRepetitions(0, 8, 9);

        ArrayList<Integer> complexityRange = generateNumbers(2, complexity, 9);

        int complex = 0;
        for (Integer subGrid : subGridOrder) {
            fillSubGridWithZeros(subGrid, complexityRange.get(complex));
            complex++;
        }
    }


    /**
     * Fill a sub grid on the board with digits at least one digit set on it
     *
     * @param subGrid    The subGrid on the board
     * @param complexity The number of digits to be set on subGrid
     */
    public void fillSubGridWithDigits(int subGrid, int complexity) {
        ArrayList<Integer> randomCell = generateNumbersWithoutRepetitions(0, 8, complexity);
        ArrayList<Integer> randomDigits = generateNumbersWithoutRepetitions(1, 9, complexity);
        int minSetters = 0;
        int digit = 0;
        List<Cell> subGridCells = getSubGrid(subGrid);
        for (Integer cellPos : randomCell) {
            Cell cell = subGridCells.get(cellPos);
            Integer value = randomDigits.get(digit);
            if (isSaveSetCell(cell, value)) {
                setCell(cell, value);
                minSetters++;
            }
            digit++;
        }
        if (minSetters == 0) {
            fillSubGridWithDigits(subGrid, complexity);
        }
    }

    public void fillSubGridWithZeros(int subGrid, int complexity) {
        ArrayList<Integer> randomCell = generateNumbersWithoutRepetitions(0, 8, complexity);
        List<Cell> subGridCells = getSubGrid(subGrid);
        for (Integer cellPos : randomCell) {
            Cell cell = subGridCells.get(cellPos);
            setCell(cell, 0);
        }
    }

    /**
     * Verify if a num is can be set in a cell on the board
     *
     * @param cell A Cell object on the board
     * @param num  A digit number
     * @return Return true if the num is not used in row, column and sub grid
     */
    public Boolean isSaveSetCell(Cell cell, int num) {
        Boolean isPracticableInColumn = !isNumUsedInColumnCell(cell, num);
        Boolean isPracticableInRow = !isNumUsedInCellRow(cell, num);
        Boolean isPracticableInSubGrid = !isNumUsedInSubGrid(cell, num);
        return isPracticableInColumn && isPracticableInRow && isPracticableInSubGrid;
    }

    /**
     * Generate a range of numbers between [min... max]
     *
     * @param max The maximum number
     * @return The list of random numbers generated without repetition
     */
    public static ArrayList<Integer> generateNumbersWithoutRepetitions(int min, int max, int size) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < size) {
            int random = randomGenerator.nextInt((max - min) + 1) + min;
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        return numbers;
    }

    /**
     * Generate a range of numbers between [min... max]
     *
     * @param max The maximum number
     * @return The list of random numbers generated
     */
    public static ArrayList<Integer> generateNumbers(int min, int max, int size) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        while (numbers.size() < size) {
            int random = randomGenerator.nextInt((max - min) + 1) + min;
            numbers.add(random);
        }
        return numbers;
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    /**
     * Convert an array of integers to an array of Cells
     *
     * @param arrayBoard An array of integers
     */
    public void parseToCells(int[][] arrayBoard) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                this.board[row][column] = new Cell(row, column, arrayBoard[row][column]);
            }
        }
    }

    /**
     * Convert an array of integers to an array of Cells
     */
    public void parseToEmptyCells() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                this.board[row][column] = new Cell(row, column);
            }
        }
    }

    /**
     * Get an specific cell from the board
     *
     * @param row    The row position in the board
     * @param column The column position in the board
     * @return The Cell object
     */
    public Cell getCell(int row, int column) {
        return this.board[row][column];
    }

    /**
     * Set a value in an specific cell from the board
     *
     * @param cell A Cell object on the board
     */
    public void setCell(Cell cell, int value) {
        int row = cell.getPosX();
        int column = cell.getPosY();
        getCell(row, column).setValue(value);
    }

    /**
     * Set a zero value in an specific cell from the board
     *
     * @param cell A Cell object on the board
     */
    public void clearCell(Cell cell) {
        setCell(cell, 0);
    }

    /**
     * Verify if there is an empty cell in the board
     *
     * @return The condition of the board if it contains an empty cell
     */
    public Boolean hasAnEmptyCell() {
        return getFirstEmptyCell() != null;
    }

    /**
     * Get an empty Cell from the board
     *
     * @return A Cell with an zero value or null if there isn't any empty cell
     */
    public Cell getFirstEmptyCell() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (getFirstEmptyCellInRow(row) != null)
                return getFirstEmptyCellInRow(row);
        }
        return null;
    }

    /**
     * Get an empty Cell from a row of the board
     *
     * @param row The row value on the board
     * @return A Cell with an zero value or null if there isn't any empty cell
     */
    public Cell getFirstEmptyCellInRow(int row) {
        for (int column = 0; column < BOARD_SIZE; column++) {
            if (getCell(row, column).isEmpty()) {
                return getCell(row, column);
            }
        }
        return null;
    }

    /**
     * Verify if there is an cell with a num value in an specific column
     *
     * @param cell A Cell object on the board
     * @param num  A digit number
     * @return The condition of a column if it contains a cell with a num value
     */
    public boolean isNumUsedInColumnCell(Cell cell, int num) {
        int column = cell.getPosY();
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (getCell(row, column).hasValue(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if there is an cell with a num value in an specific row
     *
     * @param cell A Cell object on the board
     * @param num  A digit number
     * @return The condition of a row if it contains a cell with a num value
     */
    public boolean isNumUsedInCellRow(Cell cell, int num) {
        int row = cell.getPosX();
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (getCell(row, col).hasValue(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if there is an cell with a num value in an specific subGrid
     *
     * @param cell A Cell object on the board
     * @param num  A digit number
     * @return The condition of a subGrid if it contains a cell with a num value
     */
    public boolean isNumUsedInSubGrid(Cell cell, int num) {
        Cell initCell = getIniCellSubGrid(cell);
        int iniRow = initCell.getPosX();
        int iniCol = initCell.getPosY();
        for (int row = iniRow; row < iniRow + 3; row++) {
            if (isNumUsedInSubGridRow(row, iniCol, num))
                return true;
        }
        return false;
    }

    /**
     * Verify if there is an cell with a num value in an specific row
     *
     * @param row    The row position in the board
     * @param iniCol The ini column position on sub grid
     * @param num    A digit number
     * @return The condition of a row if it contains a cell with a num value
     */
    public boolean isNumUsedInSubGridRow(int row, int iniCol, int num) {
        for (int col = iniCol; col < iniCol + 3; col++) {
            if (getCell(row, col).hasValue(num)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Get the initial position of a sub grid according an value
     *
     * @param num A digit number
     * @return The init position on a sub grid
     */
    public static int getIniPosSubGrid(int num) {
        int modRes = num % 3;
        return num - modRes;
    }

    /**
     * Get the initial cell of a sub grid
     *
     * @param cell A Cell object on the board
     * @return The init cell on a sub grid
     */
    public Cell getIniCellSubGrid(Cell cell) {
        int row = cell.getPosX();
        int column = cell.getPosY();
        int subGridIniRow = getIniPosSubGrid(row);
        int subGridIniCol = getIniPosSubGrid(column);
        return getCell(subGridIniRow, subGridIniCol);
    }

    /**
     * Get the initial cell of a sub grid
     *
     * @return The init cell on a sub grid
     */
    public Cell getIniCellSubGrid(int num) {
        int row = (num / 3) * 3;
        int col = (num % 3) * 3;
        //  0  1  2   3  4  5   6  7  8 ---> SubGrid number
        // 00 03 06  30 33 36  60 63 66 ----> Cell init position
        int subGridIniRow = getIniPosSubGrid(row);
        int subGridIniCol = getIniPosSubGrid(col);
        return getCell(subGridIniRow, subGridIniCol);
    }

    /**
     * Get the initial cell of a sub grid
     *
     * @return The init cell on a sub grid
     */
    public List<Cell> getSubGrid(int num) {
        List<Cell> subGrid = new ArrayList<Cell>();
        Cell initCell = getIniCellSubGrid(num);
        int iniRow = initCell.getPosX();
        int iniCol = initCell.getPosY();
        for (int row = iniRow; row < iniRow + 3; row++) {
            for (int col = iniCol; col < iniCol + 3; col++) {
                subGrid.add(getCell(row, col));
            }
        }
        return subGrid;
    }

    @Override
    public String toString() {
        String line = "------+-------+--------";
        StringBuilder board = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (row != 0 && row % 3 == 0) {
                board.append(line).append("\n");
            }
            appendRow(row, board);
        }
        return board.toString();
    }

    public StringBuilder appendRow(int row, StringBuilder board){
        int col = 0;
        while (col < BOARD_SIZE) {
            int i;
            for (i = col; i < col + 3; i++) {
                appendCell(getCell(row, i), board);
            }
            board.append("| ");
            col = i;
        }
        return board.append("\n");
    }

    public StringBuilder appendCell(Cell cell, StringBuilder board){
        if (cell.isEmpty()) {
            board.append(". ");
        } else {
            board.append(cell.getValue()).append(" ");
        }
        return board;
    }
}
