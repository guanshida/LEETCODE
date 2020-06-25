package com.leetcode.zixue.dp;

import com.leetcode.trie.Trie;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/word-break/
 *
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * 通过次数60,911提交次数134,399
 */
public class WordBreak {

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak("a", Arrays.asList(new String[]{"a"})));
        System.out.println(wb.wordBreak("aaaaaaaa", Arrays.asList(new String[]{"aaaaa", "aaa"})));
        System.out.println(wb.wordBreak("leetcode", Arrays.asList(new String[]{"leet", "code"})));
        System.out.println(wb.wordBreak("applepenapple", Arrays.asList(new String[]{"apple", "pen"})));
        System.out.println(wb.wordBreak("catsandog", Arrays.asList(new String[]{"cats", "dog", "sand", "and", "cat"})));
    }

    /**
     * 解法一： dp + 字典树： 定义 dp[i] 表示 s 字符串中前i个字符是否满足条件。 则递推公式为： dp[i] = 至少存在一个j ，使得 i>j>0 并且 dp[j]=true ，并且，s(j,i) 子串在字典中，则 dp[i] = true。不存在则为false。
     *          初始化dp[0] = true;  匹配 s.sub(j,i) 是否在 字典中，可以使用 字典树。
     *          优化： 查找是否存在j时，只需要满足 i>j>i-depth    depth 表示字典树的深度。
     *                  Time: O(n*m*m)                Space: O(n+k)           n代表字符串长度。 m代表字典树的深度。k代表所有字典的长度之和。
     *
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {

        Trie trie = new Trie();
        int depth = 0;
        for (String word : wordDict) {
            trie.insert(word);
            depth = Math.max(depth, word.length());
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 0; i < s.length(); i++) {

            for (int j = i; j >= 0 && j - i <= depth; j--) {
                if (dp[j]) {
                    dp[i + 1] = trie.search(s.substring(j, i + 1));
                    if (dp[i + 1]) {
                        break;
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }

}
