package com.leetcode.zixue.string;


import java.math.BigInteger;

/**
 * https://leetcode-cn.com/problems/add-binary/
 *67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * 通过次数92,807提交次数174,399
 */
public class AddBinary {

    public static void main(String[] args) {
        AddBinary a = new AddBinary();
        System.out.println(a.addBinary1("11", "1"));
        System.out.println(a.addBinary2("11", "1"));
        System.out.println(a.addBinary3("11", "1"));
    }

    /**
     * 解法一：先转换成数字，然后求和，再转换成字符串。
     *          Time: O(n)      Space: O(1)    n代表a和b 字符串的最大长度。
     *
     * 解法二： 直接按照二进制加法进行相加。
     *          Time: O(n)          Space: O(1)
     * @param a
     * @param b
     * @return
     */
    public String addBinary1(String a, String b) {
        BigInteger aInt = new BigInteger(a, 2);
        BigInteger bInt = new BigInteger(b, 2);
        return aInt.add(bInt).toString(2);
    }

    public String addBinary2(String a, String b) {
        StringBuilder sba = new StringBuilder();
        sba.append(a);
        sba.reverse();

        StringBuilder sbb = new StringBuilder();
        sbb.append(b);
        sbb.reverse();

        return addBinaryReverse(sba.toString(), sbb.toString());
    }

    private String addBinaryReverse(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int jin = 0;
        int temp;
        for (int i = 0; i < a.length() || i < b.length(); i++) {
            temp = jin;
            if (i < a.length()) {
                temp += a.charAt(i) - '0';
            }
            if (i < b.length()) {
                temp += b.charAt(i) - '0';
            }
//            temp += i < a.length() ? a.charAt(i) - '0' : 0;
//            temp += i < b.length() ? b.charAt(i) - '0' : 0;
            if (temp < 2) {
                sb.append(temp);
                jin = 0;
            } else {
                sb.append(temp - 2);
                jin = 1;
            }
        }
        if (jin != 0) {
            sb.append(jin);
        }

        return sb.reverse().toString();
    }

    public String addBinary3(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        StringBuilder sb = new StringBuilder(Math.max(aLen, bLen) + 1);
        int jin = 0;
        int temp;
        for (int i = 0; i < a.length() || i < b.length(); i++) {
            temp = jin;
            temp += i < aLen ? a.charAt(aLen - i - 1) - '0' : 0;
            temp += i < bLen ? b.charAt(bLen - i - 1) - '0' : 0;
            if (temp < 2) {
                sb.append(temp);
                jin = 0;
            } else {
                sb.append(temp - 2);
                jin = 1;
            }
        }
        if (jin != 0) {
            sb.append(jin);
        }

        return sb.reverse().toString();


    }

}
