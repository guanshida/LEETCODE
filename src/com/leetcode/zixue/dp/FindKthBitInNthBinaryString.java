package com.leetcode.zixue.dp;

import java.util.BitSet;
import java.util.Random;

/**
 * 1545. 找出第 N 个二进制字符串中的第 K 位
 * 给你两个正整数 n 和 k，二进制字符串  Sn 的形成规则如下：
 *
 * S1 = "0"
 * 当 i > 1 时，Si = Si-1 + "1" + reverse(invert(Si-1))
 * 其中 + 表示串联操作，reverse(x) 返回反转 x 后得到的字符串，而 invert(x) 则会翻转 x 中的每一位（0 变为 1，而 1 变为 0）。
 *
 * 例如，符合上述描述的序列的前 4 个字符串依次是：
 *
 * S1 = "0"
 * S2 = "011"
 * S3 = "0111001"
 * S4 = "011100110110001"
 * 请你返回  Sn 的 第 k 位字符 ，题目数据保证 k 一定在 Sn 长度范围以内。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 1
 * 输出："0"
 * 解释：S3 为 "0111001"，其第 1 位为 "0" 。
 * 示例 2：
 *
 * 输入：n = 4, k = 11
 * 输出："1"
 * 解释：S4 为 "011100110110001"，其第 11 位为 "1" 。
 * 示例 3：
 *
 * 输入：n = 1, k = 1
 * 输出："0"
 * 示例 4：
 *
 * 输入：n = 2, k = 3
 * 输出："1"
 *
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= k <= 2n - 1
 * 通过次数13,699提交次数23,423
 * @author guanshida
 * @date 2023/1/18
 */
public class FindKthBitInNthBinaryString {

    /**
     * 解法一：dp 算出 f(n)
     * 解法二：递归  算出 f(n)
     * 解法三：递归，不必要算出 f(n)
     * @param n
     * @param k
     * @return
     */
    public char findKthBit1(int n, int k) {
        StringBuilder sb = new StringBuilder();
        sb.append('0');
        for (int i = 2; i <= n; i++) {
            StringBuilder sb1 = reverseInvert(sb);
            sb.append('1').append(sb1);
        }
        return sb.charAt(k - 1);
    }

    private StringBuilder reverseInvert(StringBuilder sb) {
        StringBuilder tmp = new StringBuilder();
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (sb.charAt(i) == '0') {
                tmp.append('1');
            } else {
                tmp.append('0');
            }
        }
        return tmp;
    }



    public char findKthBit2(int n, int k) {
        String sb = dg(n);
        return sb.charAt(k - 1);
    }

    private String dg(int n) {
        if (n == 1) {
            return "0";
        }
        String s = dg(n - 1);

//        return s + "0" + reverseInvert(s).toString();
        return null;
    }


    public static void main(String[] args) {
        // a 死亡次数
        int aNum = 0;
        // b 死亡次数
        int bNum = 0;
        for (int i = 0; i < 10000; i++) {
            char c = duiJue();
            if (c == 'a') {
                aNum ++;
            } else if (c == 'b') {
                bNum ++;
            }
        }
        System.out.println("a 死亡次数：" + aNum);
        System.out.println("b 死亡次数：" + bNum);
    }
    /**
     * 返回 a，代表 a 死了。返回 b，代表 b 死了。
     * @return
     */
    public static char duiJue() {
        Random random = new Random();
        while (true) {
            // a开枪
            int i = random.nextInt(100);
            if (i < 30) {
                // a命中,b死
                return 'b';
            }
            // b开枪
            i = random.nextInt(100);
            if (i < 50) {
                // b 命中，a 死
                return 'a';
            }
        }
    }
}
