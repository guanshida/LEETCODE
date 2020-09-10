package com.leetcode.zixue.num;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * 通过次数95,635提交次数149,340
 */
public class CombinationSumIi {

    public static void main(String[] args) {
        CombinationSumIi a = new CombinationSumIi();
        System.out.println(a.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(a.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }

    /**
     * 解法：递归+剪枝
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (target == 0 || candidates.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        Set<String> visit = new HashSet<>();
        digui(candidates, 0, target, res, list, visit);
        return res;
    }

    private void digui(int[] candidates, int start, int target, List<List<Integer>> res, List<Integer> list, Set<String> visit) {
        if (target == 0 && !visit.contains(list.toString())) {
            res.add(new ArrayList<>(list));
            visit.add(list.toString());
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            list.add(candidates[i]);
            digui(candidates, i + 1, target - candidates[i], res, list, visit);
            list.remove(list.size() - 1);
        }
    }
}
