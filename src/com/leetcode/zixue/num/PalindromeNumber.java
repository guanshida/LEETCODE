package com.leetcode.zixue.num;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 *
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        PalindromeNumber pn = new PalindromeNumber();
        System.out.println(pn.isPalindrome1(121));
        System.out.println(pn.isPalindrome1(-121));
        System.out.println(pn.isPalindrome1(31213));

        System.out.println("ddddddddddddddddddddddddd");
        System.out.println(pn.isPalindrome3(10));
        System.out.println(pn.isPalindrome3(321123));
        System.out.println(pn.isPalindrome3(121));
        System.out.println(pn.isPalindrome3(121));
        System.out.println(pn.isPalindrome3(-121));
        System.out.println(pn.isPalindrome3(31213));
    }

    /**
     * 解法一： 先转换成字符串，然后遍历一半即可。
     *          Time: O(n)      Space: O(n)     n代表整数位数。
     * 解法二： 先转换成字符串，然后翻转一下。判断是否和原始字符串相等。
     *          Time: O(n)      Space: O(n)
     *
     * 解法三： 不转换为字符串解法。
     *          Time: O(n)      Space: O(1)
     *
     *
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        }

        String str = String.valueOf(x);
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome3(int x) {
        if (x < 0) {
            return false;
        } else if (x < 10) {
            return true;
        } else if (x % 10 == 0) {
            return false;
        }

        int revertedNumber = 0;
        while (revertedNumber < x) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return revertedNumber == x || x == revertedNumber / 10;
    }
}
