package com.leetcode.zixue.dp;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 * 通过次数353,654提交次数1,119,312
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        LongestPalindromicSubstring a = new LongestPalindromicSubstring();
        System.out.println(a.longestPalindrome2("babad"));
        System.out.println(a.longestPalindrome2("cbbd"));
        System.out.println(a.longestPalindrome2("ab"));
        System.out.println(a.longestPalindrome2(""));
    }

    /**
     * 解法一：暴力求解：
     *          两遍循环，i,j，然后看i-j是否是回文串。
     *          Time: O(n^3)        Space: O(1)
     * 解法二： dp，定义dp[i][j] 为从i到j是否是回文串。那么 dp[i][j] = dp[i+1][j-1] && s[i]==s[j]
     *      临界条件：len = j -i+1。len=1 为true，len = 2时 dp[i][j] = s[i] == s[j]
     *          Time: O(n^2)        Space: O(n^2)
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if ("".equals(s)) {
            return "";
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxI = 0;
        int maxJ = 0;
        int maxLen = -1;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                int len = j - i + 1;
                if (len == 1) {
                    dp[j][i] = true;
                } else if (len == 2) {
                    dp[j][i] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[j][i] = dp[j - 1][i + 1] && s.charAt(j) == s.charAt(i);
                }
                if (dp[j][i] && maxLen < len) {
                    maxI = i;
                    maxJ = j;
                    maxLen = len;
                }
            }
        }
        return s.substring(maxI, maxJ + 1);
    }

}
