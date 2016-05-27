package com.sudoku;

/**
 * This class represent a Cell object
 *
 * @author Alejandra Arteaga
 * @author Jose Cabrera
 */
public class Cell {
    private int value;
    private int posX = 0;
    private int posY = 0;

    public Cell(int x, int y, int value) {
        this.posX = x;
        this.posY = y;
        this.value = value;
    }

    public Cell(int x, int y) {
        this.posX = x;
        this.posY = y;
        this.value = 0;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * Verify if the cell is empty
     *
     * @return The condition of the cell if it has a zero value
     */
    public boolean isEmpty() {
        return isValue(0);
    }

    /**
     * Verify if the cell has a num value
     *
     * @return The condition of the cell if it has a num value
     */
    public boolean isValue(int num) {
        if (this.value == num) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "PosX: " + getPosX() + " PosY: " + getPosY() + " Value: " + getValue();
    }
}
