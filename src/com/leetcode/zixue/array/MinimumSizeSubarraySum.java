package com.leetcode.zixue.array;

import java.util.Arrays;

public class MinimumSizeSubarraySum {

    /**
     * 1. 暴力，求出所有子数组，然后挨个判断。     原地相加，只取最小值。
     * Time：O(n^2)             Space：O(1)
     * <p>
     * 2. 滑动窗口，
     * Time: O(n)              Space: O(1)
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {

        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int sum = 0;
        int minSize = Integer.MAX_VALUE;
        for (int i = 0, j = 0; j < nums.length; j++) {
            if (nums[j] >= s) {
                return 1;
            }
            sum += nums[j];
            while (i <= j && sum >= s) {
                minSize = Math.min(minSize, j - i + 1);
                sum -= nums[i];
                i++;
            }
        }

        return minSize == Integer.MAX_VALUE ? 0 : minSize;

    }

}