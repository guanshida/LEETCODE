package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

import java.util.Enumeration;

/**
 *https://leetcode-cn.com/problems/path-sum/
 *
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 通过次数97,656提交次数194,255
 */
public class PathSum {

    /**
     * 解法一：DFS
     *          Time: O(n)          Space: O(m)         n 代表节点数量。m代表层数。
     *
     * 解法二：BFS
     *          Time: O(n)          Space: O(m)         n 代表节点数量。m代表层数。
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) {
            return false;
        }
        return DFS(root, 0, sum);
    }

    private boolean DFS(TreeNode root, int i, int sum) {
        i += root.val;
        if (root.left == null && root.right == null) {
            return i == sum;
        }
        boolean flag = false;
        if (root.left != null && DFS(root.left, i, sum)) {
            return true;
        }
        return root.right != null && DFS(root.right, i, sum);
    }
}
