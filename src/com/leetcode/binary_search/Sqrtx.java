package com.leetcode.binary_search;

/**
 * https://leetcode-cn.com/problems/sqrtx/description/
     实现 int sqrt(int x) 函数。
     计算并返回 x 的平方根，其中 x 是非负整数。
     由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

     示例 1:

     输入: 4
     输出: 2
     示例 2:

     输入: 8
     输出: 2
     说明: 8 的平方根是 2.82842...,
     由于返回类型是整数，小数部分将被舍去。
 * @author 管世达
 * @create 2018-12-01
 **/
public class Sqrtx {
    public static void main(String[] args) {
        Sqrtx a = new Sqrtx();
        System.out.println(a.mySqrt2(Integer.MAX_VALUE, 1e-10));
        System.out.println(a.mySqrt3(Integer.MAX_VALUE, 1e-10));
        System.out.println(Math.sqrt(Integer.MAX_VALUE));
    }
    /**
     * 扩展题：public double mySqrt(double x,double preci);
     * 解法一：使用jdk的默认函数
     * 解法二：从1 到 x 使用二分查找找出目标数，注意溢出。
     *      Time: O(log(n))     Space: O(1)
     * 解法三：牛顿迭代法
     *
     * @param x
     * @return
     */

    public double mySqrt2(double x,double preci) {
        if (x == 0 || x == 1) {
            return x;
        }
        double start = 0,end = x;
        double mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (Math.abs(mid - x / mid) < preci) {
                return mid;
            } else if (mid > x / mid) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return -1;
    }
    public double mySqrt3(double x,double preci) {
        if (x == 0 || x == 1) {
            return x;
        }

        double res = x;
        while (Math.abs(res - x / res) >= preci) {
            res = (res + x / res) / 2;
        }

        return res;
    }

    /**
     * leetcode中的题。
     * @param x
     * @return
     */
    public int mySqrt(int x) {

        if (x == 0)
            return 0;
        if (x < 4) {
            return 1;
        }
        int start = 0;
        int end = x;
        int mid;
        while (start <= end) {
            mid = (end + start) / 2;
            long temp = mid * (long)mid;
            if (temp == x) {
                return mid;
            } else if (temp > x) {
                end = mid - 1;
            } else {
                long mid1 = (long)(mid + 1) * (mid + 1);
                if (mid1 == x) {
                    return mid + 1;

                } else if (mid1 > x) {
                    return mid;
                }
                start = mid + 1;
            }
        }

        return -1;
    }

}
