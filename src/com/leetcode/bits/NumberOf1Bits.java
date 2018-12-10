package com.leetcode.bits;

/**
 * https://leetcode-cn.com/problems/number-of-1-bits/
     编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。

     示例 :
     输入: 11
     输出: 3
     解释: 整数 11 的二进制表示为 00000000000000000000000000001011

     示例 2:
     输入: 128
     输出: 1
     解释: 整数 128 的二进制表示为 00000000000000000000000010000000
 * @author 管世达
 * @create 2018-12-07
 **/
public class NumberOf1Bits {
    public static void main(String[] args) {
        NumberOf1Bits a = new NumberOf1Bits();
        int i = a.hammingWeight(1);
        System.out.println(i);
    }
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int num = 0;
        while (n != 0) {
            n &= (n - 1);
            num ++;
        }
        return num;
    }
}
