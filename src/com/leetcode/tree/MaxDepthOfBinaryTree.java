package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/
     给定一个二叉树，找出其最大深度。
     二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

     说明: 叶子节点是指没有子节点的节点。

     示例：
     给定二叉树 [3,9,20,null,null,15,7]，

     3
     / \
     9  20
     /  \
     15   7
     返回它的最大深度 3 。
 * @author 管世达
 * @create 2018-11-20
 **/
public class MaxDepthOfBinaryTree {
    /**
     * 解法一：DFS：递归。
     *      Time: O(n)
     *      Space: O(n)
     * 解法二：BFS：循环（迭代）
     *      Time：O(n)
     *      Space: O(n)
     * @param root
     * @return
     */
    public int maxDepth1(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = maxDepth1(root.left);
        int right = maxDepth1(root.right);
        return Math.max(left, right) + 1;
    }

    public int maxDepth2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int max = 0;
        int row = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            max ++;
            row = queue.size();
            while (row > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                row --;
            }
        }

        return max;
    }
}
