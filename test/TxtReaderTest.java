import com.reader.AbstractReader;
import com.reader.FactoryReader;
import com.sudoku.SudokuBoard;
import junit.framework.Assert;
import org.junit.Test;

/**
 * This class represent the unit tests for the txt Reader class
 *
 * @author Jose Cabrera
 */
public class TxtReaderTest {

    private static final String USER_DIR = System.getProperty("user.dir");

    @Test
    public void readTxtFile() {
        String txtContent = "400000805030000000000700000020000060000080400000010000000603070500200000104000000";
        AbstractReader readerTxt = FactoryReader.createReader(USER_DIR + "\\test.txt");
        Assert.assertEquals("The content is the same", txtContent, readerTxt.getContentFile());
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
        AbstractReader readerTxt = FactoryReader.createReader(USER_DIR + "\\test.txt");
        SudokuBoard sudokuBoard = new SudokuBoard(grid);
        Assert.assertEquals("The SudokuBoard is the same", sudokuBoard.toString(),
                readerTxt.getSudokuBoard().toString());
    }
}