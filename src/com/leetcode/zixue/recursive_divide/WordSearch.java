package com.leetcode.zixue.recursive_divide;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/word-search/description/?envType=study-plan-v2&envId=top-100-liked
 * 79. 单词搜索
 * 中等
 * 相关标签
 * 相关企业
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *
 *
 * 提示：
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 *
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 *
 * 通过次数
 * 460.7K
 * 提交次数
 * 993.5K
 * 通过率
 * 46.4%
 * 请问您在哪类招聘中遇到此题？
 * 1/5
 * 社招
 * 校招
 * 实习
 * 未遇到
 * @author guanshida
 * @date 2023/10/18
 */
public class WordSearch {

    /**
     * 模拟就行。
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (match(board, i, j, word, 0, visit)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean match(char[][] board, int i, int j, String word, int wPos, boolean[][] visit) {

        if (wPos >= word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visit[i][j] || board[i][j] != word.charAt(wPos)) {
            return false;
        }
        visit[i][j] = true;
        boolean flag = match(board, i - 1, j, word, wPos + 1, visit)
                || match(board, i + 1, j, word, wPos + 1, visit)
                || match(board, i, j - 1, word, wPos + 1, visit)
                || match(board, i, j + 1, word, wPos + 1, visit);
        visit[i][j] = false;
        return flag;
    }


}
