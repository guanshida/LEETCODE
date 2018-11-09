package com.leetcode.zixue.tree;/**
 * Created by windSnow on 2018/11/9.
 */

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/description/
     给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     例如:
     给定二叉树: [3,9,20,null,null,15,7],

     3
     / \
     9  20
     /  \
     15   7
     返回其层次遍历结果：
     [
     [3],
     [9,20],
     [15,7]
     ]
 * @author 管世达
 * @create 2018-11-09 18:34
 **/
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal a = new BinaryTreeLevelOrderTraversal();
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

        List<List<Integer>> list = a.levelOrder2(n6);
        System.out.println(list);
    }

    /**
     * 解法一：利用广度优先遍历。
     *      Time: O(n)
     *      Space: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();

        LinkedList<TreeNode> q0 = new LinkedList<>();
        LinkedList<TreeNode> q1 = new LinkedList<>();

        if(root != null) {
            List<Integer> l = new ArrayList<>();
            l.add(root.val);
            list.add(l);
            q0.add(root);
        } else {
            return list;
        }
        while (!q0.isEmpty() || !q1.isEmpty()) {//q0 -> q1

            if (q0.isEmpty()) {//到一层的终点了,保存q1 。并且交换q0和q1
                list.add(this.getList(q1));

                LinkedList<TreeNode> q = q0;
                q0 = q1;
                q1 = q;
            }
            TreeNode temp = q0.poll();

            if(temp.left != null) {
                q1.add(temp.left);
            }
            if(temp.right != null) {
                q1.add(temp.right);
            }
        }
        return list;
    }

    public List<Integer> getList(List<TreeNode> list) {
        List<Integer> result = new ArrayList<>();
        for (TreeNode node: list) {
            result.add(node.val);
        }
        return result;
    }
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();

        if(root == null) {
            return list;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int rowNum = queue.size();
            List<Integer> row = new ArrayList<>();

            while (rowNum > 0) {
                TreeNode temp = queue.poll();
                row.add(temp.val);
                if (temp.left != null)
                    queue.add(temp.left);
                if(temp.right != null)
                    queue.add(temp.right);
                rowNum --;
            }
            list.add(row);
        }
        return list;
    }

}
