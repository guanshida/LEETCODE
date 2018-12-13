package com.leetcode.bits;


/**
 * n皇后问题的位运算版本
 * @author 管世达
 * @create 2018-12-11
 **/
public class NQueens2 {
    public static void main(String[] args) {
        NQueens2 a = new NQueens2();
        int i = a.totalNQueens(4);
        System.out.println(i);
    }
    private int count = 0;
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }
        DFS(0, 0, 0, 0, n);
        return count;
    }

    private void DFS(int row, int col, int pie, int na, int n) {
        if (row >= n) {
            count++;
            return ;
        }
        int bits = ~(col | pie | na) & ((1 << n) - 1);
        while (bits > 0) {
            int p = bits & (-bits);
            DFS(row + 1, col | p, (pie | p) << 1, (na | p) >> 1, n);
            bits &= bits - 1;
        }
    }
}