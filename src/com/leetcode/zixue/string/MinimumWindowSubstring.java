package com.leetcode.zixue.string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/minimum-window-substring/?envType=study-plan-v2&envId=top-100-liked
 * 76. 最小覆盖子串
 * 提示
 * 困难
 * 2.6K
 * 相关企业
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 *
 *
 * 注意：
 *
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 *
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 *
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 *
 * 提示：
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 *
 *
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 * 通过次数
 * 459.5K
 * 提交次数
 * 1M
 * 通过率
 * 45.3%
 * @author guanshida
 * @date 2023/9/8
 */
public class MinimumWindowSubstring {

    /**
     * 解法一：暴力。两层 for 循环。
     *      Space:O(1)    Time:O(n^2)
     * 解法二：滑动窗口，用 Map<char,num> 存储中间值，然后滑动窗口维护一个类似的接口即可。如果一样，则是了。
     *      Space:O(n) Time:O(m+n)
     * 解法三：在解法二中，需要判断两个 map 是否一致，如果把 num 每次减一，这样反过来，就不需要判断一样了，只需要判断最大值是否小于 0 即可。然后左指针滑动的时候，边滑动边维护最小值即可。
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {

        if (s.length() < t.length()) {
            return "";
        }

        return "";


    }

}
