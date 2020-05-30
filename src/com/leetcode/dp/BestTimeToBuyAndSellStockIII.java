package com.leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/description/
     给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。

     注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     示例 1:
     输入: [3,3,5,0,0,3,1,4]
     输出: 6
     解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     示例 2:

     输入: [1,2,3,4,5]
     输出: 4
     解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     示例 3:

     输入: [7,6,4,3,1]
     输出: 0
     解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 * @author 管世达
 * @create 2018-12-19
 **/
public class BestTimeToBuyAndSellStockIII {
    public static void main(String[] args) {
        int[] prices = {3,3,5,0,0,3,1,4};
        BestTimeToBuyAndSellStockIII a = new BestTimeToBuyAndSellStockIII();
        int i = a.maxProfit3(prices);
        System.out.println(i);
    }

    /**
     * 解法一：普通循环。记录两个最大的（波峰-波谷），然后相加即可。
     *      Time: O(n)      Space: O(n)
     *
     * 解法二：递归
     *
     * 解法三：dp（动态规划）
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {

        if (prices.length <= 1) {
            return 0;
        }
        int count = 2;
        long[][][] dp = new long[prices.length][2][count + 1];

        init(dp, prices);

        for (int i = 1; i < prices.length; i++) {
            for (int k = 0; k <= count; k++) {
                if (k == 0) {
                    dp[i][0][k] = dp[i - 1][0][k];
                } else {
                    dp[i][0][k] = Math.max(dp[i - 1][0][k], dp[i - 1][1][k - 1] + prices[i]);
                }
                dp[i][1][k] = Math.max(dp[i - 1][1][k], dp[i - 1][0][k] - prices[i]);
            }
        }
        return maxArray(dp[prices.length - 1][0]);
    }

    private void init(long[][][] dp, int[] prices) {
        //初始化,i=0时（第一天）除了[0,0.0]=0和[0,1,0]=-a[0]，其余的都不合法
        dp[0][1][0] = -prices[0];
        for (int k = 1; k < dp[0][0].length; k++) {
            dp[0][0][k] = Integer.MIN_VALUE;
            dp[0][1][k] = Integer.MIN_VALUE;
        }
    }

    public int maxArray(long[] arr) {
        long max = arr[0];
        for (long i : arr) {
            if (max < i) {
                max = i;
            }
        }
        return (int)max;
    }
}
