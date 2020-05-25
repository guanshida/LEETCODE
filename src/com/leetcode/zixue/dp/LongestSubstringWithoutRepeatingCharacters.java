package com.leetcode.zixue.dp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 地址：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 1. 暴力法：两层循环，第一层循环代表从哪个位置开始，第二层代表子串结束的位置。
     *      Time: O(n^2)        Space: O(n)
     * 2. 滑块法。
     *      Time: O(n)          Space: O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }
        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                //
                int size = map.size();
                max = max < size ? size : max;
                // 去掉map中值小于i的所有元素
                Integer endIndex = map.get(s.charAt(i));
                for (int j = i - size; j <= endIndex; j++) {
                    map.remove(s.charAt(j));
                }
            }
            map.put(s.charAt(i), i);
        }
        max = max < map.size() ? map.size() : max;
        return max;
    }
    public int lengthOfLongestSubstring2(String s) {
        if (s == null) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
                map.remove(s.charAt(j));
            }
            max = Math.max(max, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return max;
    }
}
