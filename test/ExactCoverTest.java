import com.algorithm.Algorithm;
import com.algorithm.exactcover.ExactCover;
import com.sudoku.SudokuBoard;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * This class represent the unit tests for the PeterNorvig class
 *
 * @author Alejandra Arteaga
 */
public class ExactCoverTest {
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
        Algorithm exactCover = new ExactCover();
        assertTrue(exactCover.solve(sudokuBoard));
        System.out.println(sudokuBoard);
    }
}
