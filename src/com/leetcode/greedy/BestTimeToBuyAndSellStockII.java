package com.leetcode.greedy;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/description/
     给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。

     注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

     示例 1:
     输入: [7,1,5,3,6,4]
     输出: 7
     解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

     示例 2:
     输入: [1,2,3,4,5]
     输出: 4
     解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

     示例 3:
     输入: [7,6,4,3,1]
     输出: 0
     解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * @author 管世达
 * @create 2018-11-16 18:32
 **/
public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args) {
        int[] prices = new int[] {1,2,3,4,5};
        BestTimeToBuyAndSellStockII a = new BestTimeToBuyAndSellStockII();
        int i = a.maxProfit1(prices);
        System.out.println(i);
    }

    /**
     * 有三个特点：
     *      1. 只有一股
     *      2. 每天可以买卖无数次
     *      3. 无交易手续费。
     * 解法一：自己想的。遍历一遍。
     *      Time: O(n)   Space: O(1)
     * 解法二：贪心算法。只要后一天比今天得价格高，就今天买，后天卖。  3个条件都必须满足
     *      Time: O(n)   Space: O(1)
     *
     *  由于解法2（贪心算法）不需要进行Integer和int的互转。所以解法2效率较高。
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {

        if(prices == null || prices.length < 2) {
            return 0;
        }
        int sum = 0;
        Integer buy = null;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1] && buy == null) {
                buy = i - 1;
            }
            if ((prices[i] < prices[i - 1]) && buy != null) {
                sum += prices[i - 1] - prices[buy];
                buy = null;
            }
            //到了末尾，还有未卖的股票，要卖了
            if (i == prices.length - 1 && buy != null) {
                sum += prices[i] - prices[buy];
                buy = null;
            }
        }

        return sum;
    }
    public int maxProfit2(int[] prices) {
        //贪心算法。
        if(prices == null || prices.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }
}

