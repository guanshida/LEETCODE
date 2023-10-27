package com.leetcode.zixue.array;

import java.util.*;

/**
 * https://leetcode.cn/problems/merge-intervals/?envType=study-plan-v2&envId=top-100-liked
 * 56. 合并区间
 * 中等
 * 相关标签
 * 相关企业
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * 通过次数
 * 706.8K
 * 提交次数
 * 1.4M
 * 通过率
 * 49.6%
 * @author guanshida
 * @date 2023/9/12
 */
public class MergeIntervals {

    public static void main(String[] args) {

    }

    /**
     * 解法一：暴力求解。
     *          Space: O(1)    Time:O(n^2)
     * 解法二：先排序，再遍历一遍即可。
     *          Space:O(1)  Time:O(nlogn)
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        ArrayList<int[]> res = new ArrayList<>();
        res.add(intervals[0]);
        for (int[] ele : intervals) {
            int[] last = res.get(res.size() - 1);
            if (ele[0] >= last[0] && ele[0] <= last[1]) {
                last[1] = Math.max(ele[1], last[1]);
            } else {
                res.add(ele);
            }
        }
        return res.toArray(new int[0][2]);
    }


}
