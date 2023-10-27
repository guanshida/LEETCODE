package com.leetcode.zixue.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked
 *
 * 438. 找到字符串中所有字母异位词
 * 中等
 * 1.3K
 * 相关企业
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  示例 2:
 *
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 *
 * 提示:
 *
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * 通过次数
 * 318.5K
 * 提交次数
 * 584.9K
 * 通过率
 * 54.5%
 * @author guanshida
 * @date 2023/9/8
 */
public class FindAllAnagramsInAString {

    public static void main(String[] args) {

        FindAllAnagramsInAString a = new FindAllAnagramsInAString();
        System.out.println(a.findAnagrams2("cbaebabacd","abc"));
    }
    /**
     * 解法一：把 p 搞成 sign，然后使用双指针遍历 s，相同长度的子串 sign 是否一致，一致则是异位词。
     *      Space：O(1)        Time: O(26n) = O(n)
     * 解法二： 解法一中的 sgin 其实没有必要，使用两个数组，然后直接比较数组是否相等即可。
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams2(String s, String p) {
        // 先假设 s 和 p 不为空指针
        int pLength = p.length();
        if (s.length() < pLength) {
            return new ArrayList<>();
        }
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLength; i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }
        List<Integer> res = new ArrayList<>();
        if (Arrays.equals(sCount, pCount)) {
            res.add(0);
        }

        for (int i = pLength; i < s.length(); i++) {
            int left = i - pLength + 1;
            sCount[s.charAt(i) - 'a']++;
            sCount[s.charAt(left-1) - 'a']--;
            if (Arrays.equals(sCount, pCount)) {
                res.add(left);
            }
        }
        return res;
    }

    private String getSign(int[] signArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < signArr.length; i++) {
            if (signArr[i] > 0) {
                sb.append((char) ('a' + i)).append(signArr[i]);
            }
        }
        return sb.toString();
    }

}
