import com.algorithm.Algorithm;
import com.algorithm.Backtracking;
import com.sudoku.SudokuBoard;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class represent the unit tests for the Backtracking class
 *
 * @author Alejandra Arteaga
 */
public class BacktrackingTest {
    @Test
    public void solveSudokuTest() {
        int grid[][] =
                {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {0, 0, 5, 2, 0, 6, 3, 0, 0}};

        SudokuBoard sudokuBoard = new SudokuBoard(grid);
        Algorithm backtraking = new Backtracking();
        assertTrue(backtraking.solve(sudokuBoard));
    }
}
