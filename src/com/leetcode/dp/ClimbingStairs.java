package com.leetcode.dp;

/**
 * https://leetcode-cn.com/problems/climbing-stairs/description/
     假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

     注意：给定 n 是一个正整数。

     示例 1：
     输入： 2
     输出： 2
     解释： 有两种方法可以爬到楼顶。
     1.  1 阶 + 1 阶
     2.  2 阶

     示例 2：
     输入： 3
     输出： 3
     解释： 有三种方法可以爬到楼顶。
     1.  1 阶 + 1 阶 + 1 阶
     2.  1 阶 + 2 阶
     3.  2 阶 + 1 阶
 * @author 管世达
 * @create 2018-12-14
 **/
public class ClimbingStairs {
    /**
     * 解法一：回溯（递归）
     *      Time:O(2^n)
     * 解法二：递归+记忆化
     *
     * 解法三：dp（动态规划，动态递推）
     *      Time:O(n)   Space:O(n)
     *      递推公式：dp[i] = dp[i - 1] + dp[i - 2];
     * @param n
     * @return
     */
    public int climbStairs3(int n) {

        if (n <= 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int temp;
        for (int i = 2; i <= n; i++) {
            temp = cur;
            cur += pre;
            pre = temp;
        }

        return cur;
    }


    /**
     * 200613： 每日一题，再来一遍。
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int pre = 1;
        int cur = 1;
        int count = 1;
        while (count++ < n) {
            int temp = cur;
            cur = cur + pre;
            pre = temp;
        }
        return cur;

    }










}
