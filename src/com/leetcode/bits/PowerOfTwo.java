package com.leetcode.bits;

/**
 * https://leetcode-cn.com/problems/power-of-two/description/
     给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

     示例 1:
     输入: 1
     输出: true
     解释: 20 = 1

     示例 2:
     输入: 16
     输出: true
     解释: 24 = 16

     示例 3:
     输入: 218
     输出: false
 * @author 管世达
 * @create 2018-12-10
 **/
public class PowerOfTwo {
    public static void main(String[] args) {
        PowerOfTwo a = new PowerOfTwo();
        boolean flag = a.isPowerOfTwo(3);
        System.out.println(flag);
    }

    /**
     * 解法一：循环除以2，最终有没有余数。
     *
     * 解法二：2的n次方。最后的值使用二进制表示一定是一个1，n个0。以这个特性，去掉最后一个1，最终的值一定是0；
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {

        if (n <= 0) {
            return false;
        }
        if (n <= 2) {
            return true;
        }
        return (n & (n - 1)) == 0;
    }
}
