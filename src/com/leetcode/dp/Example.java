package com.leetcode.dp;

import java.util.Arrays;

/**
 * 动态规划（动态递推）理论讲解的例题
 * @author 管世达
 * @create 2018-12-13
 **/
public class Example {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,1,0},
                {0,0,0,0,1,0,0,0},
                {1,0,1,0,0,1,0,0},
                {0,0,1,0,0,0,0,0},
                {0,0,0,1,1,0,1,0},
                {0,1,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0}};
        Example a = new Example();
        int res = a.solution(grid);
        System.out.println(res);
    }
    // 0：空地。1：石头
    public int solution(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] res = new int[m][n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) { //石头
                    res[i][j] = 0;
                } else if (i == m - 1 || j == n - 1) { //边界
                    res[i][j] = 1;
                } else {
                    res[i][j] = res[i + 1][j] + res[i][j + 1];
                }
            }
        }

        for (int i = 0; i < res.length; i++) {
            System.out.println(Arrays.toString(res[i]));
        }
        return res[0][0];
    }
}
