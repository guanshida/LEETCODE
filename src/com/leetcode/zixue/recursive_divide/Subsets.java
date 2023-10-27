package com.leetcode.zixue.recursive_divide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.cn/problems/subsets/description/?envType=study-plan-v2&envId=top-100-liked
 * 78. 子集
 * 中等
 * 相关标签
 * 相关企业
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * 通过次数
 * 686.8K
 * 提交次数
 * 846.3K
 * 通过率
 * 81.2%
 * 请问您在哪类招聘中遇到此题？
 * 1/5
 * 社招
 * 校招
 * 实习
 * 未遇到
 * @author guanshida
 * @date 2023/10/17
 */
public class Subsets {

    /**
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        dg(nums, res, new ArrayList<>(), 0);
        return res;
    }

    private void dg(int[] nums, List<List<Integer>> res, List<Integer> curRes, int pos) {

        res.add(new ArrayList<>(curRes));
        if (pos >= nums.length) {
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            curRes.add(nums[i]);
            dg(nums, res, curRes, i + 1);
            curRes.remove(curRes.size() - 1);
        }
    }

}
