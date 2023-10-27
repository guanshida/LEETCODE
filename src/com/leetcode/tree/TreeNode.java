package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by windSnow on 2018/11/6.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public static TreeNode valueOf(Integer[] arr) {
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < arr.length) {
            TreeNode poll = queue.poll();
            Integer value = arr[i++];
            if (value != null) {
                poll.left = new TreeNode(value);
                queue.add(poll.left);
            }
            ;
            if (i < arr.length && (value = arr[i++]) != null) {
                poll.right = new TreeNode(value);
                queue.add(poll.right);
            }
        }
        return root;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}