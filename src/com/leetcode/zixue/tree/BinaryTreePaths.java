package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-tree-paths/
     给定一个二叉树，返回所有从根节点到叶子节点的路径。
     说明: 叶子节点是指没有子节点的节点。

     示例:
     输入:

     1
     /   \
     2     3
     \
     5

     输出: ["1->2->5", "1->3"]
     解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * @author 管世达
 * @create 2019-03-15
 **/
public class BinaryTreePaths {
    public static void main(String[] args) {

    }

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> list = new ArrayList<>();
        if (root != null) {
            DFS(root, list, root.val + "");
        }
        return list;
    }

    private void DFS(TreeNode root, List<String> list, String path) {
        if (root.left == null && root.right == null) {
            list.add(path);
            return ;
        }
        if (root.left != null) {
            this.DFS(root.left, list, path + "->" + root.left.val);
        }
        if (root.right != null) {
            this.DFS(root.right, list, path + "->" + root.right.val);
        }
    }
}
