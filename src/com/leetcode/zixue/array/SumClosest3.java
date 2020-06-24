package com.leetcode.zixue.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/3sum-closest/
 *
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 *
 *
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 * 通过次数114,348提交次数255,363
 */
public class SumClosest3 {
    public static void main(String[] args) {

        SumClosest3 a = new SumClosest3();
        System.out.println(a.threeSumClosest(new int[]{1, 1, -1}, 2));
        System.out.println(a.threeSumClosest(new int[]{0, 2, 1}, 0));
        System.out.println(a.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));

    }

    /**
     * 思路和 Sum3 一样。
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int res = target >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        int temp;

        for (int i = 0; i < nums.length - 2; i++) {
            temp = sumClosest2(nums, i + 1, nums.length - 1, target - nums[i]);
            res = Math.abs(res - target) > Math.abs(temp + nums[i] - target) ? temp + nums[i] : res;
        }
        return res;
    }

    private int sumClosest2(int[] nums, int start, int end, int target) {
        if (start >= end) {
            return nums[start];
        }
        int temp;
        int res = target >= 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        while (start < end) {
            temp = nums[start] + nums[end];
            res = Math.abs(res - target) > Math.abs(temp - target) ? temp : res;
            if (temp > target) {
                end--;
            } else if (temp < target) {
                start++;
            } else {
                return temp;
            }
        }

        return res;
    }
}
