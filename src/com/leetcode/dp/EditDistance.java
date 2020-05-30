package com.leetcode.dp;

/**
 * 编辑距离
 * https://leetcode-cn.com/problems/edit-distance/
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
     你可以对一个单词进行如下三种操作：

     插入一个字符
     删除一个字符
     替换一个字符
     示例 1:

     输入: word1 = "horse", word2 = "ros"
     输出: 3
     解释:
     horse -> rorse (将 'h' 替换为 'r')
     rorse -> rose (删除 'r')
     rose -> ros (删除 'e')
     示例 2:

     输入: word1 = "intention", word2 = "execution"
     输出: 5
     解释:
     intention -> inention (删除 't')
     inention -> enention (将 'i' 替换为 'e')
     enention -> exention (将 'n' 替换为 'x')
     exention -> exection (将 'n' 替换为 'c')
     exection -> execution (插入 'u')
 * @author 管世达
 * @create 2019-01-04
 **/
public class EditDistance {


    /**
     * 解法：dp
     *      Time: O(m*n)        Space: O(m*n)
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        boolean emptyWord1 = this.isEmpty(word1);
        boolean emptyWord2 = this.isEmpty(word2);
        if (emptyWord1 && emptyWord2) {
            return 0;
        }
        if (emptyWord1 || emptyWord2) {
            return emptyWord1 ? word2.length() : word1.length();
        }

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // 初始化
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
    public boolean isEmpty(String str) {
        if (str == null || "".equals(str))
            return true;
        return false;
    }
}
