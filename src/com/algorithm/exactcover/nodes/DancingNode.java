package com.algorithm.exactcover.nodes;

/**
 * This class represent the dancing node
 *
 * @author Jose Cabrera
 */
public class DancingNode{
    protected DancingNode left, right, up, down;
    protected ColumnNode columnNode;

    public DancingNode(){
        left = right = up = down = this;
    }

    public DancingNode(ColumnNode columnNode){
        this();
        setColumnNode(columnNode);
    }

    public ColumnNode getColumnNode() {
        return columnNode;
    }

    public void setColumnNode(ColumnNode columnNode) {
        this.columnNode = columnNode;
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

    /**
     * hooks node dancingNode below current node
     * @param dancingNode
     * @return
     */
    public DancingNode hookDown(DancingNode dancingNode){
        assert (getColumnNode() == dancingNode.getColumnNode());
        dancingNode.down = this.down;
        dancingNode.down.up = dancingNode;
        dancingNode.up = this;
        this.down = dancingNode;
        return dancingNode;
    }

    /**
     * hooks the dancingNode to the right of the current node
     * @param dancingNode
     * @return
     */
    public DancingNode hookRight(DancingNode dancingNode){
        dancingNode.right = this.right;
        dancingNode.right.left = dancingNode;
        dancingNode.left = this;
        this.right = dancingNode;
        return dancingNode;
    }

    /**
     * Switch the right node to the left
     *
     */
    public void unlinkLeftToRight(){
        this.left.right = this.right;
        this.right.left = this.left;
    }

    /**
     * Switch the left node to the right
     *
     */
    public void relinkLeftToRight(){
        this.left.right = this.right.left = this;
    }

    /**
     * Switch the up node to the down
     *
     */
    public void unlinkUpToDown(){
        this.up.down = this.down;
        this.down.up = this.up;
    }

    /**
     * Switch the down node to the up
     *
     */
    public void relinkUpToDown(){
        this.up.down = this.down.up = this;
    }
}
