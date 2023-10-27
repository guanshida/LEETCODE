package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 *
 * 230. 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 *
 *
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 *
 *
 *
 *
 * 提示：
 *
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *
 *
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 * @author guanshida
 * @date 2023/3/23
 */
public class KthSmallestElementInABst {

    /**
     * 解法一：中序遍历。
     * 解法二：优先堆。——适合频繁插入的操作。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        dfs(root, k, stack);
        return stack.peek().val;
    }

    // 需要透出两个。第几小的值。值是什么。  通过一个栈解决。
    private void dfs(TreeNode root, int k, Stack<TreeNode> stack) {
        if (root == null || stack.size() > k) {
            return ;
        }
        dfs(root.left, k, stack);
        if (stack.size() < k) {
            stack.push(root);
            dfs(root.right, k, stack);

        }
    }
}
