package com.leetcode.zixue.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 *  
 *
 * 示例:
 *
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 *
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * 通过次数44,631提交次数64,778
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        ProductOfArrayExceptSelf a = new ProductOfArrayExceptSelf();
        System.out.println(Arrays.toString(a.productExceptSelf(new int[]{1, 2, 3, 4})));
    }

    /**
     * 解法一： 暴力破解
     *          Time: O(n^2)          Space: O(1)
     * 解法二： 先把所有乘积都算出来，然后遍历一遍，一直除就行。
     *          Time: O(n)          Space: O(1)
     * 解法三： 两遍遍历，同时维护一个乘积值，第一遍，正序遍历，给 arr[i] 赋值左边所有的乘积。第二遍倒序遍历，把i后面的也乘起来。
     *          Time: O(n)          Space: O(1)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {

        int[] arr = new int[nums.length];
        if (nums.length <= 1) {
            return arr;
        }

        int multi = 1;
        for (int i = 0; i < nums.length; i++) {
            arr[i] = multi;
            multi *= nums[i];
        }

        multi = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            arr[i] *= multi;
            multi *= nums[i];
        }

        return arr;
    }
}
