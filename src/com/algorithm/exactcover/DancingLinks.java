package com.algorithm.exactcover;

import com.algorithm.exactcover.nodes.ColumnNode;
import com.algorithm.exactcover.nodes.DancingNode;
import com.sudoku.SudokuBoard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represent the dancing node
 */
public class DancingLinks {

    public ColumnNode header;
    private List<DancingNode> answer;
    private SudokuBoard sudokuBoard;
    private int size = SudokuBoard.SIZE;

    /**
     * all the columns removed
     *
     * @param k
     * @return
     */
    private SudokuBoard search(int k) {
        if (header.getRight() == header) {
            sudokuBoard = parseBoard(answer);
        } else {
            ColumnNode columnNode = selectColumnNodeHeuristic();
            columnNode.cover();
            for (DancingNode r = columnNode.getDown(); r != columnNode; r = r.getDown()) {
                answer.add(r);
                for (DancingNode j = r.getRight(); j != r; j = j.getRight()) {
                    j.getC().cover();
                }
                search(k + 1);
                r = answer.remove(answer.size() - 1);
                columnNode = r.getC();
                for (DancingNode j = r.getLeft(); j != r; j = j.getLeft()) {
                    j.getC().uncover();
                }
            }
            columnNode.uncover();
        }
        return sudokuBoard;
    }

    /**
     * Parse the board
     *
     * @param answer
     * @return
     */
    private SudokuBoard parseBoard(List<DancingNode> answer) {
        this.sudokuBoard = ExactCover.board;
        SudokuBoard result = new SudokuBoard();
        for (DancingNode actualNode : answer) {
            DancingNode rcNode = actualNode;
            int min = Integer.parseInt(rcNode.getC().getName());
            for (DancingNode temp = actualNode.getRight(); temp != actualNode; temp = temp.getRight()) {
                int val = Integer.parseInt(temp.getC().getName());
                if (val < min) {
                    min = val;
                    rcNode = temp;
                }
            }
            int ans1 = Integer.parseInt(rcNode.getC().getName());
            int ans2 = Integer.parseInt(rcNode.getRight().getC().getName());
            int row = ans1 / size;
            int col = ans1 % size;
            int num = (ans2 % size) + 1;
            result.setCell(sudokuBoard.getCell(row, col), num);
        }
        return result;
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
     */
    private ColumnNode makeDancingLinksBoard(int[][] grid) {
        final int COLS = grid[0].length;
        final int ROWS = grid.length;

        System.out.println(COLS);
        System.out.println(ROWS);
        ColumnNode headerNode = new ColumnNode("header");
        ArrayList<ColumnNode> columnNodes = new ArrayList<ColumnNode>();

        for (int i = 0; i < COLS; i++) {
            ColumnNode n = new ColumnNode(Integer.toString(i));
            columnNodes.add(n);
            headerNode = (ColumnNode) headerNode.hookRight(n);
        }
        headerNode = headerNode.getRight().getC();

        for (int row = 0; row < ROWS; row++) {
            DancingNode prev = null;
            for (int j = 0; j < COLS; j++) {
                if (grid[row][j] == 1) {
                    ColumnNode col = columnNodes.get(j);
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

    /**
     * Grid consists solely of 1s and 0s. Undefined behaviour otherwise
     *
     * @param grid
     */
    public DancingLinks(int[][] grid) {
        header = makeDancingLinksBoard(grid);
    }

    /**
     * @return
     */
    public SudokuBoard runSolver() {
        answer = new LinkedList<>();
        return search(0);
    }
}