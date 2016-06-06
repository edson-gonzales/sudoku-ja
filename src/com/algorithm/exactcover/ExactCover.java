package com.algorithm.exactcover;

import com.sudoku.SudokuBoard;

import java.util.Arrays;

/**
 * This class represent the exact cover algorithm
 *
 */
public class ExactCover {

    private SudokuBoard sudokuBoard;
    private int size = SudokuBoard.SIZE;
    private int side = 3;
    // sudoku has numbers 1-9. A 0 indicates an empty cell that we will need to
    // fill in.
    private int[][] makeExactCoverGrid(int[][] sudoku){
        int[][] R = sudokuExactCover();
        for(int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                int n = sudoku[i - 1][j - 1];
                if (n != 0){ // zero out in the constraint board
                    for(int num = 1; num <= size; num++){
                        if (num != n){
                            Arrays.fill(R[getIdx(i, j, num)], 0);
                        }
                    }
                }
            }
        }
        return R;
    }

    // Returns the base exact cover grid for a SUDOKU puzzle
    private int[][] sudokuExactCover(){
        int[][] R = new int[9 * 9 * 9][9 * 9 * 4];

        int hBase = 0;

        // row-column constraints
        for(int r = 1; r <= size; r++){
            for(int c = 1; c <= size; c++, hBase++){
                for(int n = 1; n <= size; n++){
                    R[getIdx(r, c, n)][hBase] = 1;
                }
            }
        }

        // row-number constraints
        for(int r = 1; r <= size; r++){
            for(int n = 1; n <= size; n++, hBase++){
                for(int c1 = 1; c1 <= size; c1++){
                    R[getIdx(r, c1, n)][hBase] = 1;
                }
            }
        }

        // column-number constraints

        for(int c = 1; c <= size; c++){
            for(int n = 1; n <= size; n++, hBase++){
                for(int r1 = 1; r1 <= size; r1++){
                    R[getIdx(r1, c, n)][hBase] = 1;
                }
            }
        }

        // box-number constraints

        for(int br = 1; br <= size; br += side){
            for(int bc = 1; bc <= size; bc += side){
                for(int n = 1; n <= size; n++, hBase++){
                    for(int rDelta = 0; rDelta < side; rDelta++){
                        for(int cDelta = 0; cDelta < side; cDelta++){
                            R[getIdx(br + rDelta, bc + cDelta, n)][hBase] = 1;
                        }
                    }
                }
            }
        }
        return R;
    }

    // row [1,size], col [1,size], num [1,size]
    private int getIdx(int row, int col, int num){
        return (row - 1) * size * size + (col - 1) * size + (num - 1);
    }
    
    protected SudokuBoard solve(int[][] sudoku){
        int[][] cover = makeExactCoverGrid(sudoku);
        DancingLinks dlx = new DancingLinks(cover, new SolutionHandler(size));
        this.sudokuBoard = dlx.runSolver();
        return sudokuBoard;
    }

    public static void main(String[] args) {
        int[][] covers = {{3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}};
        ExactCover exactCover = new ExactCover();
        SudokuBoard sudokuBoard = new SudokuBoard(covers);
        System.out.println(exactCover.solve(covers).toString());
        System.out.println(sudokuBoard.toString());
    }
}
