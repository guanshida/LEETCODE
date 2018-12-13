package com.leetcode.bits;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/description/
 * 数独问题，尝试加入位运算
 * @author 管世达
 * @create 2018-11-28
 **/
public class SudokuSolver3 {

    private static int count = 0;
    public static void main(String[] args) {
        char[][] b = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        SudokuSolver3 a = new SudokuSolver3();
        long start = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - start + " 毫秒");
        a.solveSudoku(b);
        for (char[] s: b) {
            System.out.println(Arrays.toString(s));
        }
        System.out.println(count);
    }
    public void solveSudoku(char[][] board) {
        //已使用的，boolean[]  ,index为1~9。true，表示已使用；false表示尚未使用。
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] blocks = new int[9];

        initSudoku(board, rows, cols, blocks);
        boolean dfs = this.DFS(board, rows, cols, blocks, 0, 0);
        System.out.println(dfs);
    }

    private boolean DFS(char[][] board, int[] rows, int[] cols, int[] blocks, int i, int j) {
        //终止条件
        count++;
        do {
            while (j < 9) {
                if (board[i][j] == '.') {
                    break;
                }
                j++;
            }
            if (j >= 9) {
                i ++;
                j = 0;
            } else if (board[i][j] == '.') {
                break;
            }
        } while (i < board.length);
        if (i >= board.length) {
            return true;
        }
        //查到了下一个本次要填写的值。board[i][j]
        int bits = ~(rows[i] | cols[j] | blocks[(i / 3) * 3 + j / 3]) & ((1 << 9) - 1);
        while (bits != 0) {
            int p = bits & -bits;
            board[i][j] = (char)(bitsToInt(p) + '0');
            rows[i] |= p;
            cols[j] |= p;
            blocks[(i / 3) * 3 + j / 3] |= p;

            boolean flag = DFS(board, rows, cols, blocks, i, j + 1);
            if (flag) {
                return true;
            }

            board[i][j] = '.';
            rows[i] &= ~p;
            cols[j] &= ~p;
            blocks[(i / 3) * 3 + j / 3] &= ~p;
            bits &= bits - 1;
        }
        return false;
    }

    public int bitsToInt(int bit) {
        int k = 0;
        while (bit != 0) {
            bit = bit >> 1;
            k++;
        }
        return k;
    }
    public void initSudoku(char[][] board, int[] rows, int[] cols, int[] blocks){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                int p = 1 << (board[i][j] - '0' - 1);
                rows[i] |= p;
                cols[j] |= p;
                blocks[(i / 3) * 3 + j / 3] |= p;
            }
        }
    }
}
