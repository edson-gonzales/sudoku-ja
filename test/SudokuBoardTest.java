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
    public void isUsedInRowTest() {
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

        Boolean isUsedInRow = sudokuBoard.isUsedInRow(5, 6);
        assertTrue(isUsedInRow);
    }

    @Test
    public void isUsedInSubGridTest() {

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

        Boolean isUsedInSubGrid = sudokuBoard.isUsedInSubGrid(2, 2, 3);
        assertTrue(isUsedInSubGrid);
    }

    @Test
    public void isUsedInColumnTest() {
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
        Boolean isUsedInColumn = sudokuBoard.isUsedInColumn(5, 3);
        assertTrue(isUsedInColumn);
    }

    @Test
    public void getInitPositionForFirstSubGridTest() {
        int initPos = SudokuBoard.getIniPosSubGrid(0);
        assertEquals(0, initPos);
    }

    @Test
    public void getInitPositionForSecondSubGridTest() {
        int initPos = SudokuBoard.getIniPosSubGrid(4);
        assertEquals(3, initPos);
    }

    @Test
    public void getInitPositionForThirdSubGridTest() {
        int initPos = SudokuBoard.getIniPosSubGrid(7);
        assertEquals(6, initPos);
    }
}
