package com.leetcode.zixue.array;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger a = new ReverseInteger();
        System.out.println(a.reverse2(1534236469));
    }

    /**
     * 解法一： 使用现有工具
     * 解法二：每次取10的余数，得到一个序列。
     * @param x
     * @return
     */
    public int reverse1(int x) {
        int num = Math.abs(x);
        StringBuilder sb = new StringBuilder();
        sb.append(num);
        sb.reverse();
        try {
            Integer integer = Integer.valueOf(sb.toString());
            return x >= 0 ? integer : 0 - integer;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public int reverse2(int x) {
        int num = x;
        int res = 0;
        while (num != 0) {
            int i = num % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && i > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && i < -8)) {
                return 0;
            }
            res = res * 10 + i;
            num = num / 10;
        }
        return res;
    }

    /**
     * 用long判断是否溢出
     * @param x
     * @return
     */
    public int reverse3(int x) {
        int num = x;
        long res = 0;
        long MIN_INTEGER = Integer.MIN_VALUE;
        long MAX_INTEGER = Integer.MAX_VALUE;
        while (num != 0) {
            int i = num % 10;
            res = res * 10 + i;
            if (res > MAX_INTEGER || res < MIN_INTEGER) {
                return 0;
            }
            num = num / 10;
        }
        return (int)res;
    }

}
