import com.reader.AbstractReader;
import com.reader.FactoryReader;
import com.sudoku.SudokuBoard;
import junit.framework.Assert;
import org.junit.Test;

/**
 * This class represent the unit tests for the csv Reader class
 *
 * @author Jose Cabrera
 */
public class CsvReaderTest {

    private static final String USER_DIR = System.getProperty("user.dir");

    @Test
    public void readCsvFile() {
        String txtContent = "003020600900305001001806400008102900700000008006708200002609500800203009005010300";
        AbstractReader readerCsv = FactoryReader.createReader(USER_DIR + "\\test.csv");
        Assert.assertEquals("The content is the same", txtContent, readerCsv.getContentFile());
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
        AbstractReader readerTxt = FactoryReader.createReader(USER_DIR + "\\test.csv");
        SudokuBoard sudokuBoard = new SudokuBoard(grid);
        Assert.assertEquals("The SudokuBoard is the same", sudokuBoard.toString(),
                readerTxt.getSudokuBoard().toString());
    }
}