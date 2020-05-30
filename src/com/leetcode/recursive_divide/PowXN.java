package com.leetcode.recursive_divide;

/**
 * https://leetcode-cn.com/problems/powx-n/description/
     实现 pow(x, n) ，即计算 x 的 n 次幂函数。

     示例 1:
     输入: 2.00000, 10
     输出: 1024.00000

     示例 2:
     输入: 2.10000, 3
     输出: 9.26100

     示例 3:
     输入: 2.00000, -2
     输出: 0.25000
     解释: 2-2 = 1/22 = 1/4 = 0.25

     说明:
     -100.0 < x < 100.0
     n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * @author 管世达
 * @create 2018-11-13 19:37
 **/
public class PowXN {

    public static void main(String[] args) {
        PowXN a = new PowXN();
        System.out.println(a.myPow3(2, -2147483648));
//        int d = -2147483648;
//        long i = 0 - d;
//        System.out.println(i);
    }


    /**
     * 解法一：n个x相乘，循环。        超出时间限制
     * Time: O(n)
     * 解法二：调用库函数：Math.pow(x,n)
     * Time: O(1)
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow1(double x, int n) {

        if (n == 0) {
            return 1;
        }
        double res = 1;
        int abs = Math.abs(n);
        for (int i = 0; i < abs; i++) {
            res = res * x;
        }
        if (n > 0) {
            return res;
        }
        return 1 / res;
    }

    public double myPow2(double x, int n) {

        if (n == 0) {
            return 1;
        }
        if (x == 1) {
            return 1;
        }
        if (n < 0) {
            return myPow2(1 / x, -(n + 1)) * (1 / x);
        }
        if (n % 2 != 0) {
            return x * myPow2(x * x, n / 2);
        }
        return myPow2(x * x, n / 2);
    }

    public double myPow3(double x, int n) {
        long m = n;
        if(n < 0) {
            x = 1/x;
            m = -(long)n;
        }
        if(m == 1) {
            return x;
        }
        double pow = 1;
        while (m != 0) {
            if ((m & 1) != 0) {
                pow = pow * x;
            }
            x *=x;
            m = m>>1;
        }
        return pow;
    }

    //50. 实现 pow(x, n) ，即计算 x 的 n 次幂函数。 方法2：递归法  在csdn的收藏里
    public double myPow4(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        int t = n/2;//折半分成两部分，用来降低时间复杂度的
        if(n < 0) {//比如：x的-10次方 == x的-1次方的10次方
            x = 1/ x;
            t = -t;
        }
        double res = myPow4(x, t);//计算一半的结果

        if(n % 2 == 0) //偶数
            return res * res;
        else
            return res * res * x;
    }

    public double myPow5(double x, int n) {

        if (n == 0)
            return 1;

        if (n < 0) {
            return myPow5(1 / x, -(n - 1)) * (1 / x);
        }
        double res = myPow5(x, n/2);
        return (n & 1) == 0 ? res * res : res * res * x;
    }
}