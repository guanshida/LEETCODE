package com.leetcode.zixue.string;

/**
 * https://leetcode.cn/problems/string-compression/?envType=study-plan-v2&id=leetcode-75
 * 443. 压缩字符串
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 *
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 *
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 *
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 *
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：chars = ["a","a","b","b","c","c","c"]
 * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * 解释："aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * 示例 2：
 *
 * 输入：chars = ["a"]
 * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 * 解释：唯一的组是“a”，它保持未压缩，因为它是一个字符。
 * 示例 3：
 *
 * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 * 解释：由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
 *
 *
 * 提示：
 *
 * 1 <= chars.length <= 2000
 * chars[i] 可以是小写英文字母、大写英文字母、数字或符号
 * 通过次数76,486提交次数159,547
 * @author guanshida
 * @date 2023/4/14
 */
public class StringCompression {

    /**
     * 解法一：双指针移动。然后把慢指针以后的清空，最后返回慢指针。
     * @param chars
     * @return
     */
    public int compress(char[] chars) {

        int slow = 0;
        int fast = 0;
        while (fast < chars.length) {
            int oldFast = fast;
            chars[slow] = chars[fast];
            while (fast < chars.length && chars[slow] == chars[fast]) {
                fast++;
            }
            int space = fast - oldFast;
            if (space > 1) {
                // 计算数量，放入数字
                String tmp = String.valueOf(space);
                for (int i = 0; i < tmp.length(); i++) {
                    chars[++slow] = tmp.charAt(i);
                }
            }
            slow++;
        }
        return slow;
    }


    public static void main(String[] args) {
        StringCompression a = new StringCompression();
//        System.out.println(a.compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));;
//        System.out.println(a.compress(new char[]{'a'}));;
//        System.out.println(a.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'}));;
        System.out.println(a.compress(new char[]{'a','a','a','b','b','a','a'}));;
    }
}
