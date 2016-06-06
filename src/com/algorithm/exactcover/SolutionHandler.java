package com.algorithm.exactcover;

import com.algorithm.exactcover.nodes.DancingNode;
import com.sudoku.SudokuBoard;

import java.util.List;

/**
 * This class represent the solution handler of dancing links
 */
public class SolutionHandler{
    int size = SudokuBoard.SIZE;

    public SudokuBoard handleSolution(List<DancingNode> answer){
        int[][] result = parseBoard(answer);
        SudokuBoard sudokuBoard = new SudokuBoard(result);
        return sudokuBoard;
    }

    private int[][] parseBoard(List<DancingNode> answer){
        int[][] result = new int[size][size];
        for(DancingNode actualNode : answer){
            DancingNode rcNode = actualNode;
            int min = Integer.parseInt(rcNode.getC().getName());
            for(DancingNode temp = actualNode.getRight(); temp != actualNode; temp = temp.getRight()){
                int val = Integer.parseInt(temp.getC().getName());
                if (val < min){
                    min = val;
                    rcNode = temp;
                }
            }
            int ans1 = Integer.parseInt(rcNode.getC().getName());
            int ans2 = Integer.parseInt(rcNode.getRight().getC().getName());
            int r = ans1 / size;
            int c = ans1 % size;
            int num = (ans2 % size) + 1;
            result[r][c] = num;
        }
        return result;
    }

    public SolutionHandler(int boardSize){
        size = boardSize;
    }
}