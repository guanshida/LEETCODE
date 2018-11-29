package com.leetcode.pruning;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/description/
     编写一个程序，通过已填充的空格来解决数独问题。

     一个数独的解法需遵循如下规则：

     数字 1-9 在每一行只能出现一次。
     数字 1-9 在每一列只能出现一次。
     数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
     空白格用 '.' 表示。



     一个数独。



     答案被标成红色。

     Note:

     给定的数独序列只包含数字 1-9 和字符 '.' 。
     你可以假设给定的数独只有唯一解。
     给定数独永远是 9x9 形式的。
 * @author 管世达
 * @create 2018-11-28
 **/
public class SudokuSolver {

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
        SudokuSolver a = new SudokuSolver();
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
        Map<Integer, boolean[]> rows = new HashMap<>(); // key:行号：0~8
        Map<Integer, boolean[]> cols = new HashMap<>();// key:列号：0~8
        Map<Integer, boolean[]> blocks = new HashMap<>();// key:块号：0~8
        initMap(rows);
        initMap(cols);
        initMap(blocks);

        initSudoku(board, rows, cols, blocks);
        this.DFS(board, rows, cols, blocks, 0, 0);
    }

    private void initMap(Map<Integer, boolean[]> map) {
        for (int i = 0; i < 9; i++) {
            boolean[] temp = new boolean[10];
            map.put(i, temp);
        }
    }

    private boolean DFS(char[][] board, Map<Integer, boolean[]> rows, Map<Integer, boolean[]> cols, Map<Integer, boolean[]> blocks, int i, int j) {
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
        boolean[] row = rows.get(i);
        boolean[] col = cols.get(j);
        boolean[] block = blocks.get((i / 3) * 3 + j / 3);
        for (int k = 1; k < 10; k++) {
            if (row[k] || col[k] || block[k]) {
                continue;
            }
            //递归
            row[k] = true;
            col[k] = true;
            block[k] = true;
            board[i][j] = (char)(k + '0');

            boolean flag = DFS(board, rows, cols, blocks, i, j + 1);

            //恢复现场,当有正确结果时，不需要恢复现场了，直接返回，也不需要进行以后的递归。
            if (flag) {
                return true;
            }
            row[k] = false;
            col[k] = false;
            block[k] = false;
            board[i][j] = '.';
        }
        return false;
    }

    public void initSudoku(char[][] board, Map<Integer, boolean[]> rows, Map<Integer, boolean[]> cols, Map<Integer, boolean[]> blocks){
        for (int i = 0; i < board.length; i++) {
            boolean[] row = rows.get(i);
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                boolean[] col = cols.get(j);
                boolean[] block = blocks.get((i / 3) * 3 + j / 3);

                int index = board[i][j] - '0';
                row[index] = true;
                col[index] = true;
                block[index] = true;
            }
        }
    }
}
