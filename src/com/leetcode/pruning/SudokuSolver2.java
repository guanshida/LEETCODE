package com.leetcode.pruning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/description/
 * 解数独问题，优化
 *      从选项少的先处理。并且加上预处理
 * @author 管世达
 * @create 2018-11-28
 **/
public class SudokuSolver2 {
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
        SudokuSolver2 a = new SudokuSolver2();
        long start = System.currentTimeMillis();
        a.solveSudoku(b);
        System.out.println(System.currentTimeMillis() - start + " 1毫秒");
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
        long start = System.currentTimeMillis();
        List<Square> list = new ArrayList<>(100);
        optimization(board, rows, cols, blocks, list);
        System.out.println(System.currentTimeMillis() - start + " 毫秒");
        Collections.sort(list, new Comparator<Square>() {
            @Override
            public int compare(Square o1, Square o2) {
                return o1.value.size() - o2.value.size();
            }
        });


        this.DFS(board, list, 0, rows, cols, blocks);
    }

    private void optimization(char[][] board, Map<Integer, boolean[]> rows, Map<Integer, boolean[]> cols, Map<Integer, boolean[]> blocks, List<Square> list) {
        for (int i = 0; i < board.length; i++) {
            boolean[] row = rows.get(i);
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    continue;
                }
                boolean[] col = cols.get(j);
                boolean[] block = blocks.get((i / 3) * 3 + j / 3);
                Square s = new Square(i,j);
                list.add(s);

                for (int k = 1; k < 10; k++) {
                    if (!row[k] && !col[k] && !block[k]) {
                        s.value.add(k);
                    }
                }
            }
        }
    }

    private void initMap(Map<Integer, boolean[]> map) {
        for (int i = 0; i < 9; i++) {
            boolean[] temp = new boolean[10];
            map.put(i, temp);
        }
    }

    private boolean DFS(char[][] board, List<Square> list, int index, Map<Integer, boolean[]> rows, Map<Integer, boolean[]> cols, Map<Integer, boolean[]> blocks) {
        count++;
        //终止条件
        if (index >= list.size()) {
            return true;
        }
        Square square = list.get(index);

        int i = square.row;
        int j = square.col;
        boolean[] row = rows.get(i);
        boolean[] col = cols.get(j);
        boolean[] block = blocks.get((i / 3) * 3 + j / 3);
        for (int v = 0; v < square.value.size(); v++) {
            int k = square.value.get(v);
            if (row[k] || col[k] || block[k]) {
                continue;
            }
            //递归
            row[k] = true;
            col[k] = true;
            block[k] = true;
            board[i][j] = (char)(k + '0');

            boolean flag = DFS(board, list, index + 1, rows, cols, blocks);

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

    class Square {
        int row;
        int col;
        List<Integer> value = new ArrayList<>();

        Square(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
