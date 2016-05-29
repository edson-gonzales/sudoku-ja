import com.sudoku.Cell;
import com.sudoku.SudokuBoard;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class represent the unit tests for the Sudoku class
 *
 * @author Alejandra Arteaga
 */
public class SudokuBoardTest {
    @Test
    public void isNumUsedInCellRow() {
        int grid[][] = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},// row 5
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        SudokuBoard sudokuBoard = new SudokuBoard(grid);
        Cell cell = sudokuBoard.getCell(5,5);
        Boolean isNumUsedInCellRow = sudokuBoard.isNumUsedInCellRow(cell, 6);
        assertTrue(isNumUsedInCellRow);
    }

    @Test
    public void isNumUsedInSubGrid() {

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
        Cell cell = sudokuBoard.getCell(2,2);
        Boolean isNumUsedInSubGrid = sudokuBoard.isNumUsedInSubGrid(cell, 3);
        assertTrue(isNumUsedInSubGrid);
    }

    @Test
    public void isNumUsedInColumnCell() {
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
        Cell cell = sudokuBoard.getCell(5,5);
        Boolean isNumUsedInColumnCell = sudokuBoard.isNumUsedInColumnCell(cell, 3);
        assertTrue(isNumUsedInColumnCell);
    }

    @Test
    public void getIniPosFirstSubGridTest() {
        int initPos = SudokuBoard.getIniPosSubGrid(0);
        assertEquals(0, initPos);
    }

    @Test
    public void getIniPosSecondSubGridTest() {
        int initPos = SudokuBoard.getIniPosSubGrid(4);
        assertEquals(3, initPos);
    }

    @Test
    public void getIniPosThirdSubGridTest() {
        int initPos = SudokuBoard.getIniPosSubGrid(7);
        assertEquals(6, initPos);
    }
}
