package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/description/
     给定一个二叉树，找出其最小深度。
     最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

     说明: 叶子节点是指没有子节点的节点。

     示例:
     给定二叉树 [3,9,20,null,null,15,7],

     3
     / \
     9  20
     /  \
     15   7
     返回它的最小深度  2.

 *
 * 说明：类似于找最大。MaxDepthOfBinaryTree.java
 * @author 管世达
 * @create 2018-11-20
 **/
public class MinDepthOfBinaryTree {
     public int minDepth1_1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        } else if(root.left != null && root.right != null) {
            return Math.min(minDepth1_1(root.left), minDepth1_1(root.right)) + 1;
        } else if(root.left != null) {
            return minDepth1_1(root.left) + 1;
        } else {
            return minDepth1_1(root.right) + 1;
        }
    }
    public int minDepth1_2(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            return Math.min(minDepth1_2(root.left), min);
        }
        if (root.right != null) {
            return Math.min(minDepth1_2(root.right), min);
        }
        return min;
    }
    public int minDepth1_3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = minDepth1_3(root.left);
        int right = minDepth1_3(root.right);
        if(left == 0 || right == 0) {
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

    public int minDepth2(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int min = 0;
        int row;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            min ++;
            row = queue.size();
            while (row > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return min;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                row --;
            }
        }
        return min;
    }
}