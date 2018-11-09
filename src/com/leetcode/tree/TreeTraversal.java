package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 广度优先遍历
 * Created by windSnow on 2018/11/9.
 */
public class TreeTraversal {
    public static void main(String[] args) {
        TreeTraversal a = new TreeTraversal();
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n8 = new TreeNode(8);
        TreeNode n0 = new TreeNode(0);
        TreeNode n4 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        n6.left = n2;
        n6.right = n8;
        n2.left = n0;
        n2.right = n4;
        n8.left = n7;
        n8.right = n9;
        n4.left = n3;
        n4.right = n5;

        List<TreeNode> list = a.breadthFirstTraversal(n6);
        System.out.println(list);
    }
    public List<TreeNode> breadthFirstTraversal(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();

        LinkedList<TreeNode> queue = new LinkedList<>();
        if(root != null) {
            queue.addLast(root);
        } else {
            return list;
        }
        while(!queue.isEmpty()) {
            TreeNode temp = queue.pollFirst();
            list.add(temp);
            if(temp.left != null) {
                queue.addLast(temp.left);
            }
            if(temp.right != null) {
                queue.addLast(temp.right);
            }
        }
        return list;
    }
}
