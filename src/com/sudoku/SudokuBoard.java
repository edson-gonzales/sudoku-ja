package com.sudoku;

/**
 * This class represent the Sudoku board
 *
 * @author Alejandra Arteaga
 * @author Jose Cabrera
 */
public class SudokuBoard {
    private static int BOARD_SIZE = 9;
    private Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

    public SudokuBoard(int[][] arrayBoard) {
        parseToCells(arrayBoard);
    }

    public int getBoardSize() {
        return BOARD_SIZE;
    }

    public SudokuBoard(Cell[][] board) {
        this.board = board;
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
     * @param row    The row position in the board
     * @param column The column position in the board
     */
    public void setCell(int row, int column, int value) {
        getCell(row, column).setValue(value);
    }

    /**
     * Set a zero value in an specific cell from the board
     *
     * @param row    The row position in the board
     * @param column The column position in the board
     */
    public void clearCell(int row, int column) {
        getCell(row, column).setValue(0);
    }

    /**
     * Get an empty Cell from the board
     *
     * @return A Cell with an zero value or null if there isn't any empty cell
     */
    public Cell getFirstEmptyCell() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (getCell(row, column).isEmpty()) {
                    return getCell(row, column);
                }
            }
        }
        return null;
    }

    /**
     * Verify if there is an cell with a num value in an specific column
     *
     * @param column The column position in the board
     * @param num    A digit number
     * @return The condition of a column if it contains a cell with a num value
     */
    public boolean isUsedInColumn(int column, int num) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (getCell(row, column).isValue(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if there is an cell with a num value in an specific row
     *
     * @param row The row position in the board
     * @param num A digit number
     * @return The condition of a row if it contains a cell with a num value
     */
    public boolean isUsedInRow(int row, int num) {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (getCell(row, col).isValue(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if there is an cell with a num value in an specific subGrid
     *
     * @param row    The row position in the board
     * @param column The column position in the board
     * @param num    A digit number
     * @return The condition of a subGrid if it contains a cell with a num value
     */
    public boolean isUsedInSubGrid(int row, int column, int num) {
        int initPosX = getIniPosSubGrid(row);
        int initPosY = getIniPosSubGrid(column);
        for (int i = initPosX; i < initPosX + 3; i++) {
            for (int j = initPosY; j < initPosY + 3; j++) {
                if (getCell(i, j).isValue(num)) {
                    return true;
                }
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

    @Override
    public String toString() {
        String line = "------+------+------";
        StringBuilder cadena = new StringBuilder();

        for (int row = 0; row < BOARD_SIZE; row++) {
            if (row != 0 && row % 3 == 0) {
                cadena.append(line).append("\n");
            }
            int col = 0;
            while (col < BOARD_SIZE) {
                int i;
                for (i = col; i < col + 3; i++) {
                    if (getCell(row, i).isEmpty()) {
                        cadena.append(". ");
                    } else {
                        cadena.append(getCell(row, i).getValue()).append(" ");
                    }
                }
                cadena.append("|");
                col = i;
            }
            cadena.append("\n");
        }
        return cadena.toString();
    }
}
