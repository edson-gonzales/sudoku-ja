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
     * @param num    A digit number
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
     * @param num A digit number
     * @return The condition of a row if it contains a cell with a num value
     */
    public boolean isNumUsedInCellRow(Cell cell, int num) {
        int row =  cell.getPosX();
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
     * @param num    A digit number
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
     * @param row           The row position in the board
     * @param iniCol The ini column position on sub grid
     * @param num           A digit number
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

    @Override
    public String toString() {
        String line = "------+-------+--------";
        StringBuilder board = new StringBuilder();

        for (int row = 0; row < BOARD_SIZE; row++) {
            if (row != 0 && row % 3 == 0) {
                board.append(line).append("\n");
            }
            int col = 0;
            while (col < BOARD_SIZE) {
                int i;
                for (i = col; i < col + 3; i++) {
                    if (getCell(row, i).isEmpty()) {
                        board.append(". ");
                    } else {
                        board.append(getCell(row, i).getValue()).append(" ");
                    }
                }
                board.append("|").append(" ");
                col = i;
            }
            board.append("\n");
        }
        return board.toString();
    }
}
