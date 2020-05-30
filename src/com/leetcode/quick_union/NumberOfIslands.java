package com.leetcode.quick_union;

import com.leetcode.bits.NumberOf1Bits;
import com.leetcode.tree.DFS;

/**
 * https://leetcode-cn.com/problems/number-of-islands/
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

     示例 1:

     输入:
     11110
     11010
     11000
     00000

     输出: 1
     示例 2:

     输入:
     11000
     11000
     00100
     00011

     输出: 3
 * @author 管世达
 * @create 2019-01-05
 **/
public class NumberOfIslands {
    public static void main(String[] args) {
//        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][] grid = {{'1'},{'1'}};
        NumberOfIslands a = new NumberOfIslands();
        int i = a.numIslands2(grid);
        System.out.println(i);
    }
    /**
     * 解法一：DFS（深度优先搜索）
     *      Time: O(n*m)    Space: O(1)
     *
     * 解法二：并查集
     * @param grid
     * @return
     */
    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '1') {
                    continue;
                }
                count ++;
                this.DFS(grid, i, j);
            }
        }

        return count;
    }

    private void DFS(char[][] grid, int i, int j) {
        grid[i][j] = '2';
        if (i > 0 && grid[i - 1][j] == '1') {
            this.DFS(grid, i - 1, j);
        }
        if (j > 0 && grid[i][j - 1] == '1') {
            this.DFS(grid, i, j - 1);
        }
        if (i < grid.length - 1 && grid[i + 1][j] == '1') {
            this.DFS(grid, i + 1, j);
        }
        if (j < grid[i].length - 1 && grid[i][j + 1] == '1') {
            this.DFS(grid, i, j + 1);
        }
    }
    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        QUUF qu = new QUUF(grid);
        int n = grid.length;
        int m = grid[0].length;
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0')
                    continue;
                for (int k = 0; k < 2; k++) {
                    if (valide(grid, i + dx[k], j + dy[k]) && grid[i + dx[k]][j + dy[k]] == '1') {
                        qu.union(i * m + j, (i + dx[k]) * m + j + dy[k]);
                    }
                }
            }
        }

        return qu.count;
    }

    private boolean valide(char[][] grid, int i, int j) {
        return i >= 0 && j >= 0 && i < grid.length && j < grid[i].length;
    }

    class QUUF {
        private int[] roots = null;
        private int count = 0;
        private QUUF(char[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            roots = new int[n * m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == '1') {
                        roots[i * m + j] = i * m + j;
                        this.count++;
                    }
                }
            }
        }

        public int find(int i) {

            int root = i;
            while (root != roots[root]) {
                root = roots[root];
            }
            // 需要数据压缩
            if (roots[i] != root) {
                while (i != roots[i]) {
                    int tmp = roots[i];
                    roots[i] = root;
                    i = tmp;
                }
            }
            return root;
        }

        public boolean connected(int i, int j) {
            return find(i) == find(j);
        }

        public void union(int i, int j) {
            int rooti = find(i);
            int rootj = find(j);
            if (rooti != rootj) {
                roots[rooti] = rootj;
                this.count--;
            }
        }
    }
}
