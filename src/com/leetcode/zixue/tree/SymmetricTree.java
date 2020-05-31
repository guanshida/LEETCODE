package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。

  

 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

 1
 / \
 2   2
 / \ / \
 3  4 4  3
  

 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

 1
 / \
 2   2
 \   \
 3    3
  

 进阶：

 你可以运用递归和迭代两种方法解决这个问题吗？

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/symmetric-tree
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 管世达
 * @create 2020-05-31
 **/
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        // 08
        LinkedList<TreeNode> list = new LinkedList<>();

        if (root == null) {
            return true;
        }
        list.addLast(root);

        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size/2; i++) {

            }

            for (int i = 0; i < size; i++) {
                TreeNode treeNode = list.pollFirst();
                if (treeNode != null) {

                }

            }
        }

        return true;
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(3);

        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;

        SymmetricTree a = new SymmetricTree();
        System.out.println(a.isSymmetric2(n0));
    }

    public boolean isSymmetric2(TreeNode root) {
        // 08
        return digui(root, root);
    }

    private boolean digui(TreeNode root, TreeNode root1) {

        if (root == null && root1 == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root.val) {
            return false;
        }


        return digui(root.left, root1.right) && digui(root.right, root1.left);
    }


}
