package com.algorithm.exactcover.nodes;

/**
 * This class represent the Column node
 *
 * @author Jose Cabrera
 */
public class ColumnNode extends DancingNode {
    private int size;
    private String name;

    public ColumnNode(String n) {
        super();
        size = 0;
        name = n;
        columnNode = this;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Removes a column from the matrix as well as remove all
     * rows in the column from other columns that are in the matrix
     *
     */
    public void cover() {
        unlinkLeftToRight();
        for (DancingNode i = this.down; i != this; i = i.down) {
            for (DancingNode j = i.right; j != i; j = j.right) {
                j.unlinkUpToDown();
                j.columnNode.size--;
            }
        }
        size--;
    }

    /**
     * Put the rows back by traveling up the column and to the left of the row.
     * Then put the column back to undo the operation of cover.
     */
    public void uncover() {
        for (DancingNode i = this.up; i != this; i = i.up) {
            for (DancingNode j = i.left; j != i; j = j.left) {
                j.columnNode.size++;
                j.relinkUpToDown();
            }
        }
        relinkLeftToRight();
        size++;
    }
}