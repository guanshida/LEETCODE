package com.leetcode.recursive_divide;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/majority-element/description/
     给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     你可以假设数组是非空的，并且给定的数组总是存在众数。

     示例 1:
     输入: [3,2,3]
     输出: 3

     示例 2:
     输入: [2,2,1,1,1,2,2]
     输出: 2
 * @author 管世达
 * @create 2018-11-14 17:08
 **/
public class MajorityElement {
    /**
     * 解法一：暴力，两层循环，第二层求第一层出现的个数，大于n/2时返回。
     *      Time: O(n^2)     Space: O(1)
     * 解法二：使用map计数。求最大的一个。
     *      Time: O(n)       Space: O(n)
     * 解法三：先排序。然后取中间值。即可。
     *      Time：O(nlogn)   Space: O(1)
     * 解法四：分治。
     *      Time: O(nlogn)   Space: O(nlogn)
     * 解法五：投票算法
     *      由于count(众数) > n/2  所以，众数比其他的所有加起来还要多。因此可以采用此方法。
     *      初始count=1，result = nums[0],遍历，result为空,则为result赋值。如果和result相同+1，不同-1，则最后的值result就是众数
     *      Time: O(n)      Space: O(1)
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {


        return 0;
    }

    public int majorityElement5(int[] nums) {
        int count = 0;
        Integer result = null;
        for (int i : nums) {
            if(result == null || count == 0) {
                result = i;
            }
            count += i == result ? 1 : -1;
        }

        return result;
    }
}
