package com.leetcode.zixue.recursive_divide;

/**
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 * 面试题46. 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *  
 *
 * 提示：
 *
 * 0 <= num < 231
 * 通过次数13,072提交次数24,804
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 注意：01，不能翻译成b。只能翻译成ab
 */
public class BaShuZiFanYiChengZiFuChuanLcof {

    public static void main(String[] args) {
        BaShuZiFanYiChengZiFuChuanLcof a = new BaShuZiFanYiChengZiFuChuanLcof();
        System.out.println(a.translateNum1(506));
        System.out.println(a.translateNum1(12258));


        System.out.println(a.translateNum2(506));
        System.out.println(a.translateNum2(12258));
        System.out.println(a.translateNum2(1));
        System.out.println(a.translateNum2(12));
        System.out.println(a.translateNum2(44));
    }

    /**
     * 解法一：先把数字转换成字符串，然后递归。
     *          Time: O(2^n)        Space: O(n)     n代表字符长度。
     * 解法二： 动态规划。可以理解成走台阶。每次可以走一步，或者条件满足时可以走两步。问有多少走法。
     *      定义状态：dp[i] 表示str到i位时有多少种解法。那么 dp[i] = dp[i-1] + (isValid(str[i-1],str[i])? dp[i-2]:0)   isValid(str[i-1],str[i-2]) 表示 str[i-1],str[i-2] 是合法的。即>26
     *          Time: O(n)          Space: O(n)     n代表字符长度。
     *
     *          优化一下可以把Space优化成O(1)
     * @param num
     * @return
     */
    public int translateNum1(int num) {
        if (num < 10) {
            return 1;
        }
        String s = String.valueOf(num);
        return digui(s, 0);
    }

    private int digui(String str, int i) {
        if (i >= str.length() - 1) {
            return 1;
        }
        return digui(str, i + 1) + (isValid(str.charAt(i), str.charAt(i + 1)) ? digui(str, i + 2) : 0);
    }

    public int translateNum2(int num) {
        if (num < 10 || (num > 25 && num < 100)) {
            return 1;
        }
        if (num < 100) {
            return 2;
        }
        String str = String.valueOf(num);
        int[] dp = new int[str.length()];
        dp[0] = 1;
        dp[1] = isValid(str.charAt(0), str.charAt(1)) ? 2 : 1;
        for (int i = 2; i < str.length(); i++) {
            dp[i] = dp[i - 1] + (this.isValid(str.charAt(i - 1), str.charAt(i)) ? dp[i - 2] : 0 );
        }
        return dp[dp.length - 1];
    }

    private boolean isValid(char c1, char c2) {
        return (c1 - '0' == 1) || (c1 - '0' == 2 && c2 - '0' < 6);
    }

}
