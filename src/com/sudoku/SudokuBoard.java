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
                board[row][column] = new Cell(row, column, arrayBoard[row][column]);
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
     * Verify if there is an empty cell in the board
     *
     * @return The condition of the board if it contains an empty cell
     */
    public Boolean isThereAnEmptyCell() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (getCell(row, column).isCellEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get an empty Cell from the board
     *
     * @return A Cell with an zero value or null if there isn't any empty cell
     */
    public Cell getEmptyCell() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (getCell(row, column).isCellEmpty()) {
                    return getCell(row, column);
                }
            }
        }
        return null;
    }

    /**
     * Verify if there is an cell with a num value in an specific column
     *
     * @return The condition of a column if it contains a cell with a num value
     */
    public boolean isUsedInColumn(int col, int num) {
        for (int row = 0; row < 9; row++) {
            if (getCell(row, col).getValue() == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if there is an cell with a num value in an specific row
     *
     * @return The condition of a row if it contains a cell with a num value
     */
    public boolean isUsedInRow(int row, int num) {
        for (int col = 0; col < 9; col++) {
            if (getCell(row, col).getValue() == num) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify if there is an cell with a num value in an specific subGrid
     *
     * @return The condition of a subGrid if it contains a cell with a num value
     */
    public boolean isUsedInSubGrid(int row, int column, int num) {
        int initPosX = getIniPosSubGrid(row);
        int initPosY = getIniPosSubGrid(column);
        for (int i = initPosX; i < initPosX + 3; i++) {
            for (int j = initPosY; j < initPosY + 3; j++) {
                if (getCell(i, j).getValue() == num) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the initial position of a sub grid according an value
     *
     * @param value The value of a position
     * @return The init position on a sub grid
     */
    public int getIniPosSubGrid(int value) {
        int modRes = value % 3;
        return value - modRes;
    }

    @Override
    public String toString() {
        String cadena = " ";
        for (int posX = 0; posX < BOARD_SIZE; posX++) {
            for (int posY = 0; posY < BOARD_SIZE; posY++) {
                cadena = cadena + getCell(posX, posY).getValue() + " ";
            }
            cadena = cadena + "\n" + " ";
        }
        return cadena;
    }
}
