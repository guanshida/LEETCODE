package com.leetcode.zixue.array;

/**
 * https://leetcode.cn/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-100-liked
 * 53. 最大子数组和
 * 中等
 * 6.3K
 * 相关企业
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组 是数组中的一个连续部分。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * 通过次数
 * 1.5M
 * 提交次数
 * 2.7M
 * 通过率
 * 54.9%
 * @author guanshida
 * @date 2023/9/8
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        MaximumSubarray a = new MaximumSubarray();
        System.out.println(a.maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(a.maxSubArray2(new int[]{5, 4, -1, 7, 8}));
        System.out.println(a.maxSubArray2(new int[]{1}));
        System.out.println(a.maxSubArray2(new int[]{-2, 1}));

        System.out.println("*************");
        System.out.println(a.maxSubArray3(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(a.maxSubArray3(new int[]{5, 4, -1, 7, 8}));
        System.out.println(a.maxSubArray3(new int[]{1}));
        System.out.println(a.maxSubArray3(new int[]{-2, 1}));
    }
    /**
     * 解法一：暴力求解，两层循环。
     *          Space: O(1)         Time: O(n^2)
     * 解法二： sum(i,j) = sum(0,j) - sum(0,i-1)，i<=j 。要想 sum(i,j) 最大，则在固定 j 的情况下，需要满足 i<=j && sum(0,i-1)最小。
     *          也即固定 j 的情况下。max(sum(i,j)) = sum(0,j)- min(sum(0,i-1))   i<=j。则分别求出 sum 和 min(sum(n)) 即可。遍历两遍求出数组中每一个的值，然后遍历一遍即可求解。
     *                  Space: O(n)         Time: O(n)
     * 解法三：动态规划。假设 f(i) 为 以 i 结尾的连续最大子数组的和。则只需要求出 max(f(i)) 0<=i<=n。而 f(i) = max(f(i-1) + nums[i],nums[i])
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] sumArr = new int[length];
        int[] minSum = new int[length];

        sumArr[0] = nums[0];
        minSum[0] = 0;
        for (int i = 1; i < length; i++) {
            sumArr[i] = sumArr[i - 1] + nums[i];
            minSum[i] = Math.min(minSum[i - 1], sumArr[i - 1]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, sumArr[i] - minSum[i]);
        }
        return max;
    }
    public int maxSubArray3(int[] nums) {
        int max = nums[0];
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            max = Math.max(pre, max);
        }
        return max;
    }

}
