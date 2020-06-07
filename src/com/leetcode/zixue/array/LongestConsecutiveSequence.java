package com.leetcode.zixue.array;

import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 *
 * 128. 最长连续序列'
 *
 *
 *给定一个未排序的整数数组，找出最长连续序列的长度。

 要求算法的时间复杂度为 O(n)。

 示例:

 输入: [100, 4, 200, 1, 3, 2]
 输出: 4
 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 通过次数44,793提交次数90,657

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 管世达
 * @create 2020-06-06
 **/
public class LongestConsecutiveSequence {

    /**
     * 解法一：先排序，再遍历一遍即可。
     *          Time:O(nlogn)       Space: O(1)
     *
     * 解法二：先构建一个hash表，然后遍历，然后两层遍历，第一层，正常遍历i，第二层判断i是否在hash表中，在就加一。
     *          Time: O(n^2) 最快情况       Space: O(1)
     * 解法三；基于解法二进行优化，当 存在 x, x+1, ... x+y 时，那么从 x+2 或者x+3 遍历一定不是最大值。所以判断hash表中是否存在 i-1 即可。
     *          Time: O(n)          Space: O(n)
     *
     * 解法四： 先构建一个hash表，然后遍历，当hash表存在时，往前遍历+往后遍历，遍历一个值，从hash表中删除一个值。循环往复。
     *          Time: O(n)          Space: O(n)
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        HashSet<Integer> set = new HashSet<>(nums.length);

        for (int i : nums) {
            set.add(i);
        }
        int maxLen = 0;
        for (int i  : nums) {
            if (!set.contains(i - 1)) {
                int cur = 1;
                int x = i+1;

                while (set.contains(x++)) {
                    cur++;
                }
                maxLen = Math.max(cur, maxLen);
            }
        }

        return maxLen;
    }
}
