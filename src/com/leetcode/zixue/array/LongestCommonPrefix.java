package com.leetcode.zixue.array;

/**
 * https://leetcode-cn.com/problems/longest-common-prefix/
 *
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix a = new LongestCommonPrefix();
        System.out.println(a.longestCommonPrefix1(new String[]{"flower", "flow", "flight"}));;
        System.out.println(a.longestCommonPrefix1(new String[]{"dog", "racecar", "car"}));;




        System.out.println(a.longestCommonPrefix3(new String[]{"flower", "flow", "flight"}));;
        System.out.println(a.longestCommonPrefix3(new String[]{"dog", "racecar", "car"}));;
    }

    /**
     * 解法一： 横向扫描，挨个遍历每个字符串，更新公共前缀。
     *          Time: O(m*n)        Space: O(1)     m为数组长度，n为字符串长度。
     * 解法二： 纵向扫描，一个字母遍历一次。
     *          Time: O(m*n)        Space: O(1)
     * 解法三： 分治，分而治之。f(i ... n) = f(f(i ... k) , f(k...n))  当i=n时，返回strs[i]   递归
     *          Time: O(m*n)        Space: O(n)
     * 解法四： 二分查找。感觉像是凑数的。性能没有任何提升，反倒有所下降。
     * @param strs
     * @return
     */
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strs[0]);
        for (int i = 1; i < strs.length; i++) {
            this.prefix(sb, strs[i]);
            if (sb.length() == 0) {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    private void prefix(StringBuilder sb, String str) {
        int i = 0;
        while (i < sb.length() && i < str.length()) {
            if (sb.charAt(i) != str.charAt(i)) {
                sb.delete(i, sb.length());
                return;
            }
            i++;
        }
        if (i < sb.length()) {
            sb.delete(i, sb.length());
        }
    }

    public String longestCommonPrefix3(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        return this.commonPrefix3(strs, 0, strs.length - 1);
    }

    private String commonPrefix3(String[] strs, int i, int j) {
        if (i >= j) {
            return strs[i];
        }
        int mid = i + (j - i) / 2;
        String left = commonPrefix3(strs, i, mid);
        String right = commonPrefix3(strs, mid+1, j);
        return prefix3(left, right);
    }

    private String prefix3(String left, String right) {
        int i = 0;
        while (i < left.length() && i < right.length()) {
            if (left.charAt(i) != right.charAt(i)) {
                break;
            }
            i++;
        }
        return left.substring(0, i);
    }
}
