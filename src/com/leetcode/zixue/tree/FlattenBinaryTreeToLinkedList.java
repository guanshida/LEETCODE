package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/?envType=study-plan-v2&envId=top-100-liked
 *
 * 114. 二叉树展开为链表
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 *
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *
 * 通过次数
 * 392.3K
 * 提交次数
 * 536.2K
 * 通过率
 * 73.2%
 * @author guanshida
 * @date 2023/10/9
 */
public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        dfs(root);
    }

    /**
     * 返回尾巴
     * @param root
     * @return
     */
    private TreeNode dfs(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftLast = dfs(root.left);
        TreeNode rightLast = dfs(root.right);

        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        if (rightLast != null) {
            return rightLast;
        } else if (leftLast != null) {
            return leftLast;
        } else {
            return root;
        }
    }
}
