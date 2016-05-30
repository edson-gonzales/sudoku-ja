import com.readers.SudokuReader;
import com.sudoku.SudokuBoard;
import junit.framework.Assert;
import org.junit.Test;

/**
 * This class represent the unit tests for the Sudoku Reader class
 *
 * @author Jose Cabrera
 */
public class SudokuReaderTest {

    @Test
    public void readTxtFile() {
        String txtContent = "400000805030000000000700000020000060000080400000010000000603070500200000104000000";
        SudokuReader readerTxt = new SudokuReader("./test.txt");
        Assert.assertEquals("The content is the same", txtContent, readerTxt.read());
    }

    @Test
    public void getSudokuFromTxtFile() {
        int grid[][] = {{4, 0, 0, 0, 0, 0, 8, 0, 5},
                {0, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 7, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 6, 0},
                {0, 0, 0, 0, 8, 0, 4, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 6, 0, 3, 0, 7, 0},
                {5, 0, 0, 2, 0, 0, 0, 0, 0},
                {1, 0, 4, 0, 0, 0, 0, 0, 0}};
        SudokuReader readerTxt = new SudokuReader("./test.txt");
        SudokuBoard sudokuBoard = new SudokuBoard(grid);
        Assert.assertEquals("The SudokuBoard is the same", sudokuBoard.toString(),
                readerTxt.getSudokuBoard().toString());
    }

    @Test
    public void readCsvFile() {
        String txtContent = "003020600,900305001,001806400,008102900,700000008,006708200,002609500,800203009,005010300";
        SudokuReader readerCsv = new SudokuReader("./test.csv");
        Assert.assertEquals("The content is the same", txtContent, readerCsv.read());
    }

    @Test
    public void getSudokuFromCsvFile() {
        int grid[][] = {{0, 0, 3, 0, 2, 0, 6, 0, 0},
                {9, 0, 0, 3, 0, 5, 0, 0, 1},
                {0, 0, 1, 8, 0, 6, 4, 0, 0},
                {0, 0, 8, 1, 0, 2, 9, 0, 0},
                {7, 0, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 6, 7, 0, 8, 2, 0, 0},
                {0, 0, 2, 6, 0, 9, 5, 0, 0},
                {8, 0, 0, 2, 0, 3, 0, 0, 9},
                {0, 0, 5, 0, 1, 0, 3, 0, 0}};
        SudokuReader readerTxt = new SudokuReader("./test.csv");
        SudokuBoard sudokuBoard = new SudokuBoard(grid);
        Assert.assertEquals("The SudokuBoard is the same", sudokuBoard.toString(),
                readerTxt.getSudokuBoard().toString());
    }
}