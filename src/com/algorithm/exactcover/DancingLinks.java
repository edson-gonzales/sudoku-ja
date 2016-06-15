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
     * Puts Exact Cover matrix into a toroidal doubly-linked list
     * and search the solution
     *
     * @return a sudoku board solved
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
            DancingNode rowAndColNode = actualNode;
            int min = Integer.parseInt(rowAndColNode.getColumnNode().getName());
            for (DancingNode iterateNode = actualNode.getRight(); iterateNode != actualNode; iterateNode = iterateNode.getRight()) {
                int val = Integer.parseInt(iterateNode.getColumnNode().getName());
                if (val < min) {
                    min = val;
                    rowAndColNode = iterateNode;
                }
            }
            int columnNodeValue = Integer.parseInt(rowAndColNode.getColumnNode().getName());
            int numDancingValue = Integer.parseInt(rowAndColNode.getRight().getColumnNode().getName());
            int row = columnNodeValue / size;
            int col = columnNodeValue % size;
            int num = (numDancingValue % size) + 1;
            sudokuBoard.setCell(sudokuBoard.getCell(row, col), num);
        }
        return sudokuBoard;
    }

    /**
     * Select a column node that accomplish with the dancing link logic
     *
     * @return minorColumnNode the minor column node
     */
    private ColumnNode selectColumnNodeHeuristic() {
        int min = Integer.MAX_VALUE;
        ColumnNode minorColumnNode = null;
        for (ColumnNode columnNode = (ColumnNode) header.getRight(); columnNode != header; columnNode = (ColumnNode) columnNode.getRight()) {
            if (columnNode.getSize() < min) {
                min = columnNode.getSize();
                minorColumnNode = columnNode;
            }
        }
        return minorColumnNode;
    }

    /**
     * Make the dancing link using the exact cover grid
     *
     * @param grid grid is a grid of 0s and 1s to solve the exact cover
     * @return the root column header node
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
            DancingNode previousNode = null;
            for (int column = 0; column < COLS; column++) {
                if (grid[row][column] == 1) {
                    ColumnNode col = columnNodes.get(column);
                    DancingNode newNode = new DancingNode(col);
                    if (previousNode == null)
                        previousNode = newNode;
                    col.getDown().hookDown(newNode);
                    previousNode = previousNode.hookRight(newNode);
                    col.setSize(col.getSize() + 1);
                }
            }
        }

        headerNode.setSize(COLS);

        return headerNode;
    }
}