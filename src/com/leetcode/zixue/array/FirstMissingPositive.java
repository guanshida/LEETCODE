package com.leetcode.zixue.array;

import java.util.BitSet;

import javax.sound.midi.Soundbank;

/**
 *
 * https://leetcode-cn.com/problems/first-missing-positive/
 *
 * 41. 缺失的第一个正数
 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。



 示例 1:

 输入: [1,2,0]
 输出: 3
 示例 2:

 输入: [3,4,-1,1]
 输出: 2
 示例 3:

 输入: [7,8,9,11,12]
 输出: 1


 提示：

 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。

 通过次数61,094提交次数156,286
 * @author 管世达
 * @create 2020-06-27
 **/
public class FirstMissingPositive {
    public static void main(String[] args) {
        FirstMissingPositive f = new FirstMissingPositive();

        System.out.println("解法二：");
        System.out.println(f.firstMissingPositive2(new int[]{1, 2, 0}));
        System.out.println(f.firstMissingPositive2(new int[]{3, 4, -1, 1}));
        System.out.println(f.firstMissingPositive2(new int[]{7, 8, 9, 11, 12}));
        System.out.println(f.firstMissingPositive3(new int[]{2, 1, 3}));
        System.out.println(f.firstMissingPositive3(new int[]{2, 1, 4}));
        System.out.println(f.firstMissingPositive3(new int[]{0, -1, 2, 1, 4}));
        System.out.println(f.firstMissingPositive3(new int[]{0, -1, 2, 1, 3}));

        System.out.println("解法三：");
       System.out.println(f.firstMissingPositive3(new int[]{1, 2, 0}));
        System.out.println(f.firstMissingPositive3(new int[]{3, 4, -1, 1}));
        System.out.println(f.firstMissingPositive3(new int[]{7, 8, 9, 11, 12}));
        System.out.println(f.firstMissingPositive3(new int[]{2, 1, 3}));
        System.out.println(f.firstMissingPositive3(new int[]{2, 1, 4}));
        System.out.println(f.firstMissingPositive3(new int[]{0, -1, 2, 1, 4}));
        System.out.println(f.firstMissingPositive3(new int[]{0, -1, 2, 1, 3}));

        System.out.println("解法4：");
        System.out.println(f.firstMissingPositive4(new int[]{1, 1}));
        System.out.println(f.firstMissingPositive4(new int[]{1, 2, 0}));
        System.out.println(f.firstMissingPositive4(new int[]{3, 4, -1, 1}));
        System.out.println(f.firstMissingPositive4(new int[]{7, 8, 9, 11, 12}));
        System.out.println(f.firstMissingPositive4(new int[]{2, 1, 3}));
        System.out.println(f.firstMissingPositive4(new int[]{2, 1, 4}));
        System.out.println(f.firstMissingPositive4(new int[]{0, -1, 2, 1, 4}));
        System.out.println(f.firstMissingPositive4(new int[]{0, -1, 2, 1, 3}));

        System.out.println("解法5：");
        System.out.println(f.firstMissingPositive5(new int[]{1, 1}));
        System.out.println(f.firstMissingPositive5(new int[]{1, 2, 0}));
        System.out.println(f.firstMissingPositive5(new int[]{3, 4, -1, 1}));
        System.out.println(f.firstMissingPositive5(new int[]{7, 8, 9, 11, 12}));
        System.out.println(f.firstMissingPositive5(new int[]{2, 1, 3}));
        System.out.println(f.firstMissingPositive5(new int[]{2, 1, 4}));
        System.out.println(f.firstMissingPositive5(new int[]{0, -1, 2, 1, 4}));
        System.out.println(f.firstMissingPositive5(new int[]{0, -1, 2, 1, 3}));
    }


    /**
     * 解法一：先排序后遍历
     *          Time: O(nlogn)          Space: O(1)
     *
     * 解法二：创建一个等长的数组，下标即为值，值为是否在数组中出现。
     *          Time: O(n)              Space: O(n)
     *      优化一下：数组可以使用位运算，即bitset。这样 space: O(n/64)  =  O(n)
     *
     * 解法三：二分+快排，先剔除掉非法的元素，只保留正整数，然后利用二分+快排思想， 先对 length/2 遍历，< 的放左边，大于的放右边，最后如果此元素所在位置 < length/2 - 1 处则说明在左边，否则在右边。
     *          Time: O(n)              Space: O(1)             如果允许对原数组变化，就是O(1) 否则就是O(n)
     *      有重复元素，此方法不通。
     *      
     * 解法四： 官方解法，遍历一遍，遍历到的值当成下标，置为负数，那么第一个为正数的索引就是缺失的值。
     *          Time: O(n)              Space: O(1)
     *
     * 解法五： 官方解法，交换数。遍历一遍i，如果 nums[i] > 0 ，则把 nums[i]-1 索引上的数与本数交换。最后遍历 一遍j nums[j] != j + 1 的值即为结果。
     *      这样让每一个数都跑到了自己该跑的位置。
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        BitSet set = new BitSet();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length) {
                set.set(nums[i]);
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.get(i)) {
                return i;
            }
        }
        return nums.length + 1;
    }

    public int firstMissingPositive3(int[] nums) {
        if (nums.length == 0) {
            return 1;
        } else if (nums.length == 1) {
            return nums[0] == 1 ? 2 : 1;
        }

        int i = kuai(nums, 0, nums.length - 1, 1);
        if (nums[i] != 1) {
            return 1;
        }
        int left = 1;
        int right = nums.length;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int kuai = kuai(nums, left, right-1, mid);
            if (kuai >= nums.length - i) {
                return nums[nums.length - 1] == nums.length - i ? nums.length - i + 1 : nums.length - i;
            } else if (nums[kuai] == mid && kuai == mid + i - 1) {
                left = mid + 1;
            } else if (nums[kuai] != mid && kuai == mid + i - 1) {
                return mid;
            } else {
                right = mid;
            }
        }


        return left;
    }

    /**
     * 快排一次，如果存在key，则返回key的索引，否则返回大于k的最小值的索引。
     * @param nums
     * @param left
     * @param right
     * @param key
     * @return
     */
    private int kuai(int[] nums, int left, int right, int key) {

        while (left < right) {
            while (nums[left] < key && left < right) {
                left++;
            }
            while (nums[right] > key && left < right) {
                right--;
            }
            if (left < right) {
                // 交换left、right
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return left;
    }

    public int firstMissingPositive4(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = nums.length + 2;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int abs = Math.abs(nums[i]) - 1;
            if (abs < nums.length && nums[abs] > 0) {
                nums[abs] = 0 - nums[abs];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                return i+1;
            }
        }
        return nums.length + 1;
    }

    public int firstMissingPositive5(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
                i--;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i+1;
            }
        }
        return nums.length + 1;
    }
}
