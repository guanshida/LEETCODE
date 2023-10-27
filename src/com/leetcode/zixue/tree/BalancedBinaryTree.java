package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

/**
 *
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * 示例 3：
 *
 * 输入：root = []
 * 输出：true
 *
 *
 * 提示：
 *
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 * 通过次数459,775提交次数800,042
 *
 *
 * @author guanshida
 * @date 2023/2/9
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return dfs(root) >= 0;
    }

    /**
     * 类似于后序遍历，当是高度平衡二叉树时，返回数的高度。当不是时返回-1。
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = dfs(root.left);
        int rightHeight = dfs(root.right);
        if (leftHeight < 0 || rightHeight < 0 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }

    }
}
