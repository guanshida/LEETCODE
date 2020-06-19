package com.leetcode.zixue.string;

/**
 * https://leetcode-cn.com/problems/valid-palindrome/
 *
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 *
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 *
 * 输入: "race a car"
 * 输出: false
 * 通过次数114,823提交次数258,766
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome a = new ValidPalindrome();
        System.out.println(a.isPalindrome2("0P"));
        System.out.println(a.isPalindrome2("A man, a plan, a canal: Panama"));
        System.out.println(a.isPalindrome2("race a car"));
        System.out.println(a.isPalindrome2(".,"));
    }

    /**
     *
     * 大小写不区分时，最好全部从小写转换为大写或者从大写转换为小写。这样就可以使用 == 判断了。
     * 解法一：遍历一遍只取有效字符，然后把s反过来，看看是否与原串一样即可。
     *          Time: O(n)          Space: O(n)
     *
     * 解法二： 两个指针，一个从左到右遍历，一个从右到左遍历，重合即为结束。
     *          Time: O(n)          Space: O(1)
     * @param s
     * @return
     */
    public boolean isPalindrome2(String s) {
        if (s.length() <= 1) {
            return true;
        }
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < s.length() && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (right >= 0 && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (left >= s.length() || right < 0) {
                return true;
            }
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
