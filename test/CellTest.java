import com.sudoku.Cell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class represent the unit tests for the Cell class
 *
 * @author Alejandra Arteaga
 */
public class CellTest {
    @Test
    public void getPosXTest() {
        Cell cell = new Cell(1, 2);
        assertEquals(1, cell.getPosX());
    }

    @Test
    public void getPosYTest() {
        Cell cell = new Cell(1, 2);
        assertEquals(2, cell.getPosY());
    }

    @Test
    public void getValueTest() {
        Cell cell = new Cell(1, 2, 5);
        assertEquals(5, cell.getValue());
    }

    @Test
    public void getDefaultValueTest() {
        Cell cell = new Cell(1, 2);
        assertEquals(0, cell.getValue());
    }

    @Test
    public void setPosXTest() {
        Cell cell = new Cell(1, 2);
        cell.setPosX(3);
        assertEquals(3, cell.getPosX());
    }

    @Test
    public void setPosYTest() {
        Cell cell = new Cell(1, 2);
        cell.setPosY(3);
        assertEquals(3, cell.getPosY());
    }

    @Test
    public void setValueTest() {
        Cell cell = new Cell(1, 2);
        cell.setValue(3);
        assertEquals(3, cell.getValue());
    }

    @Test
    public void isCellEmptyTest() {
        Cell cell = new Cell(1, 2);
        assertTrue(cell.isEmpty());
    }

}
