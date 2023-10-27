package com.leetcode.zixue.array;

/**
 * https://leetcode.cn/problems/rotate-array/description/?envType=study-plan-v2&envId=top-100-liked
 * 189. 轮转数组
 * 已解答
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 *
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 *
 * 进阶：
 *
 * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 * 通过次数
 * 702.9K
 * 提交次数
 * 1.6M
 * 通过率
 * 44.3%
 * @author guanshida
 * @date 2023/9/13
 */
public class RotateArray {

    public static void main(String[] args) {

    }
    /**
     * 解法一：把数组按照 k 分成两部分，先把一边备份，然后把另一边拷贝过去。再把备份的一边转移过来。
     *      Space: O(n)             Time:O(n)
     * 解法二：计算第i 个元素应该去的位置x，然后把i元素放入 x 位置，然后再 i=x，循环。
     *      Space: O(1)             Time: O(n)
     * 解法三：数组翻转。
     *      Space:O(1)              Time:O(n)
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int length = nums.length;
        if (k % length == 0) {
            return;
        }
        int count = 0;
        for (int start = 0; start < length && count < length; start++) {
            int i = start;
            int tmpEle = nums[i];
            do {
                // 先存储，修正
                int goalIndex = (i + k) % length;
                int temp = nums[goalIndex];
                nums[goalIndex] = tmpEle;
                tmpEle = temp;
                i = goalIndex;
                count++;
            } while (i != start);
        }



    }








}
