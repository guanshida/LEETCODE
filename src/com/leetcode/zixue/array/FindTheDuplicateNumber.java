package com.leetcode.zixue.array;

import java.util.BitSet;

/**
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 *
 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

 示例 1:

 输入: [1,3,4,2,2]
 输出: 2
 示例 2:

 输入: [3,1,3,4,2]
 输出: 3
 说明：

 不能更改原数组（假设数组是只读的）。
 只能使用额外的 O(1) 的空间。
 时间复杂度小于 O(n2) 。
 数组中只有一个重复的数字，但它可能不止重复出现一次。
 通过次数57,855提交次数89,742
 */
public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        FindTheDuplicateNumber f = new FindTheDuplicateNumber();
        int[] arr = new int[]{1, 3, 4, 2, 2};
        System.out.println(f.findDuplicate3(arr));
        System.out.println(f.findDuplicate4(arr));
        System.out.println(f.findDuplicate5(arr));
        System.out.println(f.findDuplicate6(arr));
        int[] arr2 = new int[]{3,1,3,4,2};
        System.out.println(f.findDuplicate3(arr2));
        System.out.println(f.findDuplicate4(arr2));
        System.out.println(f.findDuplicate5(arr2));
        System.out.println(f.findDuplicate6(arr2));
    }

    /**
     * 解法一：先排序，再遍历一遍取值。时间复杂度：O(nlogn)  SPace: O(n)
     *
     * 解法二：使用set存储遍历过的值。
     *          Space: O(n)     Time: O(n)
     *
     * 解法三： 方案二优化一下，使用位运算。使用 n 个二进制位表示即可 -- BitSet。
     *          Space: O(n)  n/8    Time: O(n)
     *
     * 解法四： 两层循环，i  i-n  看是否有重复的。
     *          Space: O(n^2)       Time: O(1)
     *
     * 解法五： 二分查找，先二分，循环里面再遍历。
     *          Space: O(nlogn)     Time: O(1)
     *
     * 解法六：  nums[] 数组建图，每个位置 i 连一条 i→nums[i] 的边。这样数组就是一个环，问题就变成了如何找到环的那个点。这时可以使用快慢指针的做法，快指针每次走两步，慢指针每次走一步。
     *          Space: O(n)         Time: O(1)
     *
     * 注：解法五和解法六是官方解法，忘记时可以考虑参考leetcode官网的解法：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     *
     * @param nums
     * @return
     */
    public int findDuplicate3(int[] nums) {
        BitSet set = new BitSet(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (set.get(nums[i])) {
                return nums[i];
            }
            set.set(nums[i]);
        }

        return -1;
    }

    public int findDuplicate4(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    public int findDuplicate5(int[] nums) {

        int left = 0;
        int right = nums.length - 1;
        int mid;
        int count = 0;
        int res = -1;
        while (left <= right) {
            mid = (left + right)/2;
            count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count ++;
                }
            }
            if (count <= mid) {
                left = mid + 1;
            } else {
                res = mid;
                right = mid - 1;
            }
        }

        return res;
    }

    public int findDuplicate6(int[] nums) {

        // 快慢指针，即是当前数组内容，也是下一个的下标
        int fast = nums[0], slow = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (fast != slow);

        fast = nums[0];
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return fast;
    }
}
