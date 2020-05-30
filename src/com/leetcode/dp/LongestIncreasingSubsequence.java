package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 管世达
 * @create 2018-12-26
 **/
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        LongestIncreasingSubsequence a = new LongestIncreasingSubsequence();
        int[] nums = {1,3,6,7,9,4,10,5,6};
        int i = a.lengthOfLIS2(nums);
        System.out.println(i);
    }

    /**
     * 解法一：暴力求解：每一个元素都可以选或不选。
     *      Time: O(2^n)
     * 解法二：dp
     *      dp[i] = max(dp[j]) + 1;   j < i && nums[j] < nums[i]
     *      Time: O(n^2)        Space: O(n)
     * 解法三：把dp的第二层循环换成二分查找。(和dp无关)
     *      Time: O(nlogn)      Space: O(n)
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxSub;
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            maxSub = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] > maxSub) {
                    maxSub = dp[j];
                }
            }
            dp[i] = maxSub + 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    public int lengthOfLIS3(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            int j = this.binarySearchByBig(list, nums[i]);
            if (j == -1) {
                list.add(nums[i]);
            } else {
                list.set(j, nums[i]);
            }
        }
        return list.size();
    }

    /**
     * 二分查找，返回稍大的值，如果没有，返回-1
     * @param list
     * @param num
     * @return
     */
    private int binarySearchByBig(List<Integer> list, int num) {
        if (list.get(0) > num) {
            return 0;
        }
        if (list.get(list.size() - 1) < num) {
            return -1;
        }
        int left = 0;
        int right = list.size();
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (list.get(mid) == num) {
                return mid;
            } else if (list.get(mid) > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
