package com.leetcode.zixue.dp;

/**
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 *
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 * 示例:
 *
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 *
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * 通过次数50,964提交次数118,360
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        MinimumSizeSubarraySum a = new MinimumSizeSubarraySum();
        System.out.println(a.minSubArrayLen1_2(100, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(a.minSubArrayLen1_2(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    /**
     * 解法一： 动态规划，定义 dp[i] 表示满足条件并且从i开始的最小连续子数组的长度。
     *              Time: O(n^2)            Space: O(n)         因为最坏情况下每一个i都需要遍历一遍求连续子数组的长度。
     *        优化： 由于对于 i 和 i + 1 而言，除了i元素，其他大部分都是重复的。所以可以缓存连续子数组的长度和最大值；另外也可以随时维护连续子数组的最小值，也不需要存储每一个dp值。
     *              Time: O(n)              Space: O(1)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen1_2(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0] >= s ? 1 : 0;
        }

        int maxNum = 0;
        int maxLen = 0;
        int minLen = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0) {
                maxLen--;
                maxNum -= nums[i - 1];
            }
            int j = i + maxLen;
            while (j < nums.length && maxNum < s) {
                maxNum += nums[j];
                maxLen++;
                j++;
            }
            if (maxNum >= s) {
                minLen = Math.min(maxLen, minLen);
            } else {
                break;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
