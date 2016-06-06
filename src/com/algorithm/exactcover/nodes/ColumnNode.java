package com.algorithm.exactcover.nodes;

/**
 * This class represent the Column node
 *
 */
public class ColumnNode extends DancingNode {
    private int size; // number of ones in current column
    private String name;

    public ColumnNode(String n) {
        super();
        size = 0;
        name = n;
        C = this;
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

    public void cover() {
        unlinkLeftToRight();
        for (DancingNode i = this.down; i != this; i = i.down) {
            for (DancingNode j = i.right; j != i; j = j.right) {
                j.unlinkUpToDown();
                j.C.size--;
            }
        }
        size--;
    }

    public void uncover() {
        for (DancingNode i = this.up; i != this; i = i.up) {
            for (DancingNode j = i.left; j != i; j = j.left) {
                j.C.size++;
                j.relinkUpToDown();
            }
        }
        relinkLeftToRight();
        size++; // not part of original
    }
}