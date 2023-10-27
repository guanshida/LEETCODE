package com.leetcode.zixue.graphic;

/**
 * @author guanshida
 * @date 2023/10/13
 */
public class TempTest {

    /**
     * https://leetcode.cn/problems/number-of-islands/?envType=study-plan-v2&envId=top-100-liked
     * 200. 岛屿数量
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visit = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visit[i][j] && grid[i][j] == '1') {
                    count++;
                    numIslands_dfs(grid, i, j, visit);
                }
            }
        }
        return count;
    }

    private void numIslands_dfs(char[][] grid, int i, int j, boolean[][] visit) {
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0 || grid[i][j] == '0' || visit[i][j]) {
            return;
        }
        visit[i][j] = true;
        numIslands_dfs(grid, i + 1, j, visit);
        numIslands_dfs(grid, i - 1, j, visit);
        numIslands_dfs(grid, i, j + 1, visit);
        numIslands_dfs(grid, i, j - 1, visit);
    }
}
