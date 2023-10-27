package com.leetcode.zixue.array;

import java.util.Arrays;
import java.util.Collections;

/**
 * https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked
 *
 * 283. 移动零
 * 提示
 * 简单
 * 2.1K
 * 相关企业
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 *
 * 通过次数
 * 1.1M
 * 提交次数
 * 1.8M
 * 通过率
 *
 * @author guanshida
 * @date 2023/9/5
 */
public class MoveZeroes {


    public static void main(String[] args) {
        MoveZeroes a = new MoveZeroes();
        a.moveZeroes(new int[]{0, 1, 0, 3, 12});
        a.moveZeroes(new int[]{0});
        a.moveZeroes(new int[]{});
        a.moveZeroes(new int[]{1,0});
        a.moveZeroes(new int[]{1,0,1});
    }
    /**
     * 解法一：双指针，一个指针找 0，另一个指针找非 0，找到交换，然后一直找下去。直到其中一个到队列末尾。
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int zeroIndex = 0;
        int notZeroIndex = 0;

        while (zeroIndex < nums.length && notZeroIndex < nums.length) {
            // 更新 zeroIndex 和 notZeroIndex
            while (zeroIndex < nums.length && nums[zeroIndex] != 0) {
                zeroIndex++;
            }
            if (zeroIndex > notZeroIndex) {
                notZeroIndex = zeroIndex;
            }
            while (notZeroIndex < nums.length && nums[notZeroIndex] == 0) {
                notZeroIndex++;
            }
            // 如果找到了，就交换。
            if (zeroIndex < nums.length && notZeroIndex < nums.length) {
                int tmp = nums[zeroIndex];
                nums[zeroIndex] = nums[notZeroIndex];
                nums[notZeroIndex] = tmp;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
