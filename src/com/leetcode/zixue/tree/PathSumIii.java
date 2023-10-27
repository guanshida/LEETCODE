package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

import java.util.*;

/**
 *
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 * 通过次数209,568提交次数404,135
 * @author guanshida
 * @date 2023/2/13
 */
public class PathSumIii {

    public static void main(String[] args) {

        String regex = "^scene_S2_L\\d+$";
        System.out.println("scene_S2_L2".matches(regex));
        System.out.println("scene_S2_L2_con".matches(regex));
        System.out.println("scene_S2_L02".matches(regex));
        System.out.println("scene_S2_L12".matches(regex));

        PathSumIii a = new PathSumIii();
        System.out.println("解法一");
        System.out.println(a.pathSum(TreeNode.valueOf(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1}), 8));
        a.pathSum = 0;
        System.out.println(a.pathSum(TreeNode.valueOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1}), 22));
        a.pathSum = 0;
        System.out.println(a.pathSum(TreeNode.valueOf(new Integer[]{1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000}), 0));


        System.out.println("解法二");
        System.out.println(a.pathSum2(TreeNode.valueOf(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1}), 8));
        System.out.println(a.pathSum2(TreeNode.valueOf(new Integer[]{5,4,8,11,null,13,4,7,2,null,null,5,1}), 22));
        System.out.println(a.pathSum2(TreeNode.valueOf(new Integer[]{1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000}), 0));
    }

    /**
     * 解法一：自我想出来的。不太好。
     * 解法二：前缀和。
     */
    private int pathSum = 0;
    public int pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return pathSum;
    }

    /**
     * dfs：返回为当前节点向下的所有路径和。
     * @param root
     * @param targetSum
     * @return
     */
    private List<Long> dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Long> dfsLeft = dfs(root.left, targetSum);
        List<Long> dfsRight = dfs(root.right, targetSum);

        dfsLeft.addAll(dfsRight);
        dfsLeft.add(0L);
        for (int i = 0; i < dfsLeft.size(); i++) {
            dfsLeft.set(i, dfsLeft.get(i) + root.val);
            if (dfsLeft.get(i) == targetSum) {
                // 有了
                pathSum++;
            }
        }
        return dfsLeft;
    }

    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs2(root, prefix, 0L, targetSum);
    }

    private int dfs2(TreeNode root, Map<Long, Integer> prefix, long curSum, int targetSum) {
        if (root == null) {
            return 0;
        }
        curSum += root.val;
        int res = prefix.getOrDefault(curSum - targetSum, 0);

        prefix.put(curSum, prefix.getOrDefault(curSum, 0) + 1);

        int left = dfs2(root.left, prefix, curSum, targetSum);
        int right = dfs2(root.right, prefix, curSum, targetSum);

        res = res + left + right;
        prefix.put(curSum, prefix.get(curSum) - 1);

        return res;
    }


}
