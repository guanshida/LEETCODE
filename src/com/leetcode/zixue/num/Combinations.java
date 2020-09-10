package com.leetcode.zixue.num;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combinations/
 *
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 通过次数76,952提交次数102,583
 * 在真实的面试中遇到过这道题？
 */
public class Combinations {
    public static void main(String[] args) {
        Combinations a = new Combinations();
        System.out.println(a.combine(4, 2));
        System.out.println(a.combine(4, 1));
    }

    /**
     * 解法： 递归加剪枝。
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {

        if (n < 1 || k > n) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        if (n == 1) {
            res.add(Arrays.asList(1));
            return res;
        }
        List<Integer> list = new ArrayList<>();
        digui(1, n, k, res, list);
        return res;
    }

    private void digui(int i, int n, int k, List<List<Integer>> res, List<Integer> list) {
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (i > n) {
            return;
        }
        for (int j = i; j <= n - k + 1; j++) {
            list.add(j);
            digui(j + 1, n, k - 1, res, list);
            list.remove(list.size() - 1);
        }
    }
}
