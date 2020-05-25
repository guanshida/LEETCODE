package com.leetcode.zixue.num;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/closest-divisors/
 *
 * 给你一个整数 num，请你找出同时满足下面全部要求的两个整数：
 *
 * 两数乘积等于  num + 1 或 num + 2
 * 以绝对差进行度量，两数大小最接近
 * 你可以按任意顺序返回这两个整数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：num = 8
 * 输出：[3,3]
 * 解释：对于 num + 1 = 9，最接近的两个因数是 3 & 3；对于 num + 2 = 10, 最接近的两个因数是 2 & 5，因此返回 3 & 3 。
 * 示例 2：
 *
 * 输入：num = 123
 * 输出：[5,25]
 * 示例 3：
 *
 * 输入：num = 999
 * 输出：[40,25]
 *  
 *
 * 提示：
 *
 * 1 <= num <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/closest-divisors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClosestDivisors {

    public static void main(String[] args) {
        ClosestDivisors cd = new ClosestDivisors();
        System.out.println(Arrays.toString(cd.closestDivisors(2)));
        System.out.println(Arrays.toString(cd.closestDivisors(8)));
        System.out.println(Arrays.toString(cd.closestDivisors(123)));
        System.out.println(Arrays.toString(cd.closestDivisors(999)));
    }
    public int[] closestDivisors(int num) {
        int[] arr = new int[2];
        int[] divisor1 = this.closestDivisor(num + 1);
        int[] divisor2 = this.closestDivisor(num + 2);

        if (Math.abs(divisor1[0] - divisor1[1]) < Math.abs(divisor2[0] - divisor2[1])) {
            return divisor1;
        }
        return divisor2;
    }

    int[] closestDivisor(int nums) {
        double sqrtDou = Math.sqrt(nums);

        int sqrt1 = (int) sqrtDou;

        while (nums % sqrt1 != 0) {
            sqrt1 --;
        }
        return new int[]{sqrt1, nums / sqrt1};
    }
}
