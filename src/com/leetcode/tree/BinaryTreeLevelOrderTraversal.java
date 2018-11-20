package com.leetcode.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
 * @create 2018-11-19
 **/
public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal a = new BinaryTreeLevelOrderTraversal();
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        TreeNode n4 = new TreeNode(15);
        TreeNode n5 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;

        List<List<Integer>> list = a.levelOrder2(n1);
        System.out.println(list);
    }
    /**
     * 解法一：BFS：广度优先遍历。最适合人类的思维。
     *      Time:O(n)
     * 解法二：DFS：深度优先遍历。
     *      Time:O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // Set<TreeNode> visit = new HashSet<>();
        int size;
        while (!queue.isEmpty()) {
            //每一层
            size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            result.add(temp);
        }
        return result;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }
        // Set<TreeNode> visit = new HashSet<>();

        dfs(root,0,result);
        return result;
    }

    private void dfs(TreeNode root, int i, List<List<Integer>> result) {
        List<Integer> row;
        if (result.size() < i + 1) {
            row = new ArrayList<>();
            result.add(row);
        } else {
            row = result.get(i);
        }
        row.add(root.val);

        if(root.left != null) {
            dfs(root.left, i + 1, result);
        }
        if(root.right != null) {
            dfs(root.right, i + 1, result);
        }
    }
}
