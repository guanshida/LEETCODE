package com.leetcode.zixue.num;

/**
 * https://leetcode-cn.com/problems/qiu-12n-lcof/
 * 面试题64. 求1+2+…+n
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 *
 * 输入: n = 9
 * 输出: 45
 *  
 *
 * 限制：
 *
 * 1 <= n <= 10000
 * 通过次数31,548提交次数36,716
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qiu-12n-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Qiu12nLcof {
    /**
     * 解法一： 1+...+n = (1 + n) * n / 2
     * 解法二： 循环加。
     *      很明显解法一和解法二都不能使用。
     * 解法三：递归。 f(n) = n + f(n-1)        f(1) = 1
     *      Time: O(n)      Space: O(n)
     * @param n
     * @return
     */
    public int sumNums3(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumNums3(n - 1);
    }
    public int sumNums3_2(int n) {
        boolean flag = n > 0 && (n += sumNums3_2(n - 1)) > 0;
        return n;
    }
}
