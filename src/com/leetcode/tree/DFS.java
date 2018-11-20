package com.leetcode.tree;

/**
 * 深度优先遍历
 * @author 管世达
 * @create 2018-11-19
 **/
public class DFS {

    /**
     * DFS-递归      二叉树的递归
     * @param root
     */
    public void dfs1(TreeNode root) {
        System.out.println(root.val);

        if (root.left != null) {
            dfs1(root.left);
        }
        if (root.right != null) {
            dfs1(root.right);
        }
    }
}
