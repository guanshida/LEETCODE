package com.leetcode.dp;

/**
 * https://leetcode-cn.com/problems/maximum-product-subarray/description/
     给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
     示例 1:

     输入: [2,3,-2,4]
     输出: 6
     解释: 子数组 [2,3] 有最大乘积 6。
     示例 2:

     输入: [-2,0,-1]
     输出: 0
     解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * @author 管世达
 * @create 2018-12-18
 **/
public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = {-4, -3, -2};
        MaximumProductSubarray a = new MaximumProductSubarray();
        int res = a.maxProduct3_2(nums);
        System.out.println(res);
    }
    /**
     * 解法一：暴力循环：两次循环，第一层循环，每循环一次计算i子序列的最大值。最后再求最大值。
     *      Time: O(n^2)        Space: O(1)
     * 解法二：暴力递归：每个数有可能计算，也有可能不计算，如果不计算，下一个*1；
     *      Time: O(2^n)
     * 解法三：dp，动态规划
     *      状态定义：dp[][]
     *      Time: O(n)          Space: O(1)
     * @param nums
     * @return
     */
    //变量名具体化，更易阅读。
    public int maxProduct3(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int curMax = nums[0];
        int curMin = nums[0];
        int res = nums[0];
        int temp = 0;

        for (int i = 1; i < nums.length; i++) {

            temp = Math.max(Math.max(curMax * nums[i], curMin * nums[i]), nums[i]);
            curMin = Math.min(Math.min(curMax * nums[i], curMin * nums[i]), nums[i]);
            curMax = temp;
            res = Math.max(curMax, res);
        }

        return res;
    }
    // 纯粹写dp方程，更易扩展
    public int maxProduct3_2(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        // dp[i][j]     j = 0时，表示最大，j=1时表示最小值。   i=0,=1来回切换。
        int[][] dp = new int[2][2];
        dp[0][0] = dp[0][1] = nums[0];
        int res = nums[0];
        int x,y;

        for (int i = 1; i < nums.length; i++) {

            x = i & 1;
            y = (i - 1) & 1;
            dp[x][0] = Math.max(Math.max(dp[y][0] * nums[i], dp[y][1] * nums[i]), nums[i]);
            dp[x][1] = Math.min(Math.min(dp[y][0] * nums[i], dp[y][1] * nums[i]), nums[i]);
            res = Math.max(dp[x][0], res);
        }

        return res;
    }
}
