package com.leetcode.zixue.graphic;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/rotting-oranges/description/?envType=study-plan-v2&envId=top-100-liked
 * 994. 腐烂的橘子
 * 中等
 * 相关标签
 * 相关企业
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 *
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 *
 * 输入：grid = [[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 *
 * 输入：grid = [[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] 仅为 0、1 或 2
 * 通过次数
 * 129.1K
 * 提交次数
 * 252.6K
 * 通过率
 * 51.1%
 * 请问您在哪类招聘中遇到此题？
 * 1/5
 * 社招
 * 校招
 * 实习
 * 未遇到
 * @author guanshida
 * @date 2023/10/16
 */
public class RottingOranges {

    public static void main(String[] args) {
        RottingOranges a = new RottingOranges();
        System.out.println(a.orangesRotting(new int[][]{{1},{2},{1},{1}}));
    }

    /**
     * 解法一：广度优先搜索。
     *      Space:O(m*n)              Time:O(m*n)       n 为 节点数量。
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        Queue<Node> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new Node(i, j));
                }
            }
        }

        int[] stepRow = new int[]{1, -1, 0, 0};
        int[] stepCol = new int[]{0, 0, 1, -1};

        int res = 0;
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                // 获取下一个腐烂的结果，并加入 queue 中。
                for (int j = 0; j < 4; j++) {
                    int row = poll.row + stepRow[j];
                    int col = poll.col + stepCol[j];
                    if (row >= 0 && row < m && col >= 0 && col < n && grid[row][col] == 1) {
                        grid[row][col] = 2;
                        queue.add(new Node(row, col));
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return Math.max(0, res - 1);
    }

    public class Node {
        public int row;
        public int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
