package com.leetcode.zixue.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * 2131. 连接两字母单词得到的最长回文串
 * 给你一个字符串数组 words 。words 中每个元素都是一个包含 两个 小写英文字母的单词。
 *
 * 请你从 words 中选择一些元素并按 任意顺序 连接它们，并得到一个 尽可能长的回文串 。每个元素 至多 只能使用一次。
 *
 * 请你返回你能得到的最长回文串的 长度 。如果没办法得到任何一个回文串，请你返回 0 。
 *
 * 回文串 指的是从前往后和从后往前读一样的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["lc","cl","gg"]
 * 输出：6
 * 解释：一个最长的回文串为 "lc" + "gg" + "cl" = "lcggcl" ，长度为 6 。
 * "clgglc" 是另一个可以得到的最长回文串。
 * 示例 2：
 *
 * 输入：words = ["ab","ty","yt","lc","cl","ab"]
 * 输出：8
 * 解释：最长回文串是 "ty" + "lc" + "cl" + "yt" = "tylcclyt" ，长度为 8 。
 * "lcyttycl" 是另一个可以得到的最长回文串。
 * 示例 3：
 *
 * 输入：words = ["cc","ll","xx"]
 * 输出：2
 * 解释：最长回文串是 "cc" ，长度为 2 。
 * "ll" 是另一个可以得到的最长回文串。"xx" 也是。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 105
 * words[i].length == 2
 * words[i] 仅包含小写英文字母。
 * 通过次数8,891提交次数20,801
 * @author guanshida
 * @date 2023/2/7
 */
public class LongestPalindromeByConcatenatingTwoLetterWords {

    public static void main(String[] args) {
        LongestPalindromeByConcatenatingTwoLetterWords a = new LongestPalindromeByConcatenatingTwoLetterWords();
        System.out.println(a.longestPalindrome(new String[]{"lc","cl","gg"}));
        System.out.println(a.longestPalindrome(new String[]{"ab","ty","yt","lc","cl","ab"}));
        System.out.println(a.longestPalindrome(new String[]{"cc","ll","xx"}));
        System.out.println(a.longestPalindrome(new String[]{"wc","cl","we"}));
        System.out.println(a.longestPalindrome(new String[]{"dd","aa","bb","dd","aa","dd","bb","dd","aa","cc","bb","cc","dd","cc"}));

    }

    /**
     * 方式一：找到互反的两个字符串，就长度加 4。找到标识一下，不能重复。最后再找两个相同字母的串，找到长度加 2，返回长度即可。
     *      怎么找？每一个两遍便利找。hash 表找，使用 value 作为 key 的次数。
     *      时间复杂度：O(n)       空间复杂度：O(n)
     *
     * @param words
     * @return
     */
    public int longestPalindrome(String[] words) {

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.putIfAbsent(words[i], 0);
            map.put(words[i], map.get(words[i]) + 1);
        }

        int length = 0;
        for (int i = 0; i < words.length; i++) {
            String str = "" + words[i].charAt(1) + words[i].charAt(0);
            if (!str.equals(words[i]) && map.getOrDefault(str, 0) > 0 && map.getOrDefault(words[i], 0) > 0) {
                length += 4;
                map.put(str, map.get(str) - 1);
                map.put(words[i], map.get(words[i]) - 1);
            } else if (str.equals(words[i]) && map.getOrDefault(str, 0) > 1) {
                length += 4;
                map.put(str, map.get(str) - 2);
            }
        }

        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0 && entry.getKey().charAt(0) == entry.getKey().charAt(1)) {
                length += 2;
                break;
            }
        }
        return length;

    }

    // 优化这一版貌似没有什么用。
    public int longestPalindrome_1(String[] words) {

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }

        int length = 0;
        for (int i = 0; i < words.length; i++) {
            String str = "" + words[i].charAt(1) + words[i].charAt(0);
            if (!str.equals(words[i]) && map.containsKey(str) && map.containsKey(words[i])) {
                length += 4;
                remove(map, str, 1);
                remove(map, words[i], 1);
            } else if (str.equals(words[i]) && map.getOrDefault(str, 0) > 1) {
                length += 4;
                remove(map, str, 2);
            }
        }

        for (Map.Entry<String,Integer> entry : map.entrySet()) {
            if (entry.getValue() > 0 && entry.getKey().charAt(0) == entry.getKey().charAt(1)) {
                length += 2;
                break;
            }
        }
        return length;

    }

    private void remove(Map<String, Integer> map, String str, int i) {
        if (map.get(str) <= i) {
            map.remove(str);
        } else {
            map.put(str, map.get(str) - i);
        }
    }

}
