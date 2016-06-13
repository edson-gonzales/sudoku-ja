package com.algorithm.exactcover;

import com.algorithm.exactcover.nodes.ColumnNode;
import com.algorithm.exactcover.nodes.DancingNode;
import com.sudoku.SudokuBoard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represent the dancing node
 *
 * @author Jose Cabrera
 */
public class DancingLinks {

    public ColumnNode header;
    private List<DancingNode> answer;
    private SudokuBoard sudokuBoard;
    private int size = SudokuBoard.SIZE;

    /**
     * Grid consists solely of 1s and 0s. Undefined behaviour otherwise
     *
     * @param grid
     */
    public DancingLinks(int[][] grid) {
        header = makeDancingLinksBoard(grid);
    }

    /**
     *
     * @return
     */
    public SudokuBoard searchSolution() {
        answer = new LinkedList<>();
        return search(0);
    }

    /**
     * Each Node points to another object up, down, left, right,
     * and to its corresponding ColumnNode. A special ColumnNode h
     * points to the first ColumnNode on the left as a starting point for the algorithm.
     *
     * @param iterator
     * @return A sudokuBoard Solved
     */
    private SudokuBoard search(int iterator) {
        if (header.getRight() == header) {
            sudokuBoard = parseBoard(answer);
        } else {
            ColumnNode currentColumnNode = selectColumnNodeHeuristic();
            currentColumnNode.cover();
            for (DancingNode rowNode = currentColumnNode.getDown(); rowNode != currentColumnNode; rowNode = rowNode.getDown()) {
                answer.add(rowNode);
                for (DancingNode coverNode = rowNode.getRight(); coverNode != rowNode; coverNode = coverNode.getRight()) {
                    coverNode.getColumnNode().cover();
                }
                search(iterator + 1);
                rowNode = answer.remove(answer.size() - 1);
                currentColumnNode = rowNode.getColumnNode();
                for (DancingNode uncoverNode = rowNode.getLeft(); uncoverNode != rowNode; uncoverNode = uncoverNode.getLeft()) {
                    uncoverNode.getColumnNode().uncover();
                }
            }
            currentColumnNode.uncover();
        }
        return sudokuBoard;
    }

    /**
     * Parse the board to find a solution
     *
     * @param answer the linked list to iterate using dancing links
     * @return SudokuBoard with the answer of
     */
    private SudokuBoard parseBoard(List<DancingNode> answer) {
        this.sudokuBoard = ExactCover.board;
        for (DancingNode actualNode : answer) {
            DancingNode rcNode = actualNode;
            int min = Integer.parseInt(rcNode.getColumnNode().getName());
            for (DancingNode temp = actualNode.getRight(); temp != actualNode; temp = temp.getRight()) {
                int val = Integer.parseInt(temp.getColumnNode().getName());
                if (val < min) {
                    min = val;
                    rcNode = temp;
                }
            }
            int ans1 = Integer.parseInt(rcNode.getColumnNode().getName());
            int ans2 = Integer.parseInt(rcNode.getRight().getColumnNode().getName());
            int row = ans1 / size;
            int col = ans1 % size;
            int num = (ans2 % size) + 1;
            sudokuBoard.setCell(sudokuBoard.getCell(row, col), num);
        }
        return sudokuBoard;
    }

    /**
     * Select a column node that accomplish with the dancing link logic
     *
     * @return ret
     */
    private ColumnNode selectColumnNodeHeuristic() {
        int min = Integer.MAX_VALUE;
        ColumnNode ret = null;
        for (ColumnNode columnNode = (ColumnNode) header.getRight(); columnNode != header; columnNode = (ColumnNode) columnNode.getRight()) {
            if (columnNode.getSize() < min) {
                min = columnNode.getSize();
                ret = columnNode;
            }
        }
        return ret;
    }

    /**
     * @param grid grid is a grid of 0s and 1s to solve the exact cover for
     * @return the root column header node
     *
     */
    private ColumnNode makeDancingLinksBoard(int[][] grid) {
        final int COLS = grid[0].length;
        final int ROWS = grid.length;

        ColumnNode headerNode = new ColumnNode("header");
        ArrayList<ColumnNode> columnNodes = new ArrayList<ColumnNode>();

        for (int i = 0; i < COLS; i++) {
            ColumnNode n = new ColumnNode(Integer.toString(i));
            columnNodes.add(n);
            headerNode = (ColumnNode) headerNode.hookRight(n);
        }
        headerNode = headerNode.getRight().getColumnNode();

        for (int row = 0; row < ROWS; row++) {
            DancingNode prev = null;
            for (int column = 0; column < COLS; column++) {
                if (grid[row][column] == 1) {
                    ColumnNode col = columnNodes.get(column);
                    DancingNode newNode = new DancingNode(col);
                    if (prev == null)
                        prev = newNode;
                    col.getDown().hookDown(newNode);
                    prev = prev.hookRight(newNode);
                    col.setSize(col.getSize() + 1);
                }
            }
        }

        headerNode.setSize(COLS);

        return headerNode;
    }
}