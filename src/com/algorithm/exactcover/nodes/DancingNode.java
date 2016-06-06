package com.algorithm.exactcover.nodes;

/**
 * This class represent the dancing node
 *
 */
public class DancingNode{
    protected DancingNode left, right, up, down;
    protected ColumnNode C;

    public DancingNode(){
        left = right = up = down = this;
    }

    public DancingNode(ColumnNode c){
        this();
        setC(c);
    }

    public ColumnNode getC() {
        return C;
    }

    public void setC(ColumnNode c) {
        C = c;
    }

    public DancingNode getLeft() {
        return left;
    }

    public DancingNode getRight() {
        return right;
    }

    public DancingNode getUp() {
        return up;
    }

    public DancingNode getDown() {
        return down;
    }

    // hooks node n1 `below` current node
    public DancingNode hookDown(DancingNode n1){
        assert (getC() == n1.getC());
        n1.down = this.down;
        n1.down.up = n1;
        n1.up = this;
        this.down = n1;
        return n1;
    }

    // hooke a node n1 to the right of `this` node
    public DancingNode hookRight(DancingNode n1){
        n1.right = this.right;
        n1.right.left = n1;
        n1.left = this;
        this.right = n1;
        return n1;
    }

    public void unlinkLeftToRight(){
        this.left.right = this.right;
        this.right.left = this.left;
    }

    public void relinkLeftToRight(){
        this.left.right = this.right.left = this;
    }

    public void unlinkUpToDown(){
        this.up.down = this.down;
        this.down.up = this.up;
    }

    public void relinkUpToDown(){
        this.up.down = this.down.up = this;
    }
}
