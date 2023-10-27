package com.leetcode.zixue.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 *
 * 提示：
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 * 通过次数279,205提交次数625,011
 * @author guanshida
 * @date 2023/1/30
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        MultiplyStrings a = new MultiplyStrings();
        System.out.println(a.multiply("1","0"));
        System.out.println(a.multiply("1","9"));
        System.out.println(a.multiply("9","9"));
        System.out.println(a.multiply("123","1"));
        System.out.println(a.multiply("1","123"));
        System.out.println(a.multiply("123","456"));
    }

    public String multiply(String num1, String num2) {

        if (num1 == null || num2 == null) {
            return null;
        }
        num1 = num1.trim();
        num2 = num2.trim();
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        List<StringBuilder> arr = new ArrayList<>();
        for (int i = 0; i < num2.length(); i++) {
            int num2Index = num2.length() - 1 - i;
            int jinwei = 0;
            int y = num2.charAt(num2Index) - '0';

            StringBuilder sb = new StringBuilder();
            arr.add(sb);
            for (int j = 0; j < num1.length(); j++) {
                int num1Index = num1.length() - 1 - j;
                int i1 = (num1.charAt(num1Index) - '0') * y + jinwei;
                jinwei = i1 / 10;
                sb.append(i1 % 10);
            }
            // 加上进位
            if (jinwei != 0) {
                sb.append(jinwei);
            }
            // 反转
            sb.reverse();
            // 后面加上 i 个 0
            for (int k = 0; k < i; k++) {
                sb.append("0");
            }
        }

        // 把结果相加
        StringBuilder sb = this.multiAdd(arr);
        return sb.toString();
    }

    private StringBuilder multiAdd(List<StringBuilder> arr) {
        StringBuilder sb = new StringBuilder();

        int maxlength = 0;
        for (int i = 0; i < arr.size(); i++) {
            maxlength = Math.max(arr.get(i).length(), maxlength);
        }

        int jinwei = 0;
        for (int i = 0; i < maxlength; i++) {
            int sum = jinwei;
            for (int j = 0; j < arr.size(); j++) {
                if (i < arr.get(j).length()) {
                    sum += arr.get(j).charAt(arr.get(j).length() - 1 - i) - '0';
                }
            }
            sb.append(sum % 10);
            jinwei = sum / 10;
        }
        // 加上进位
        if (jinwei != 0) {
            sb.append(jinwei);
        }
        // 反转
        return sb.reverse();
    }
}
