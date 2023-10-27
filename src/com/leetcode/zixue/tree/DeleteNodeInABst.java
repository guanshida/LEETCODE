package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

/**
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * 示例 3:
 *
 * 输入: root = [], key = 0
 * 输出: []
 *
 *
 * 提示:
 *
 * 节点数的范围 [0, 104].
 * -105 <= Node.val <= 105
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -105 <= key <= 105
 *
 *
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * @author guanshida
 * @date 2023/4/24
 */
public class DeleteNodeInABst {


    /**
     * 解法一：dfs。先遍历找出该节点，找不到直接返回。找到进行删除 delete。
     *          删除时有三种场景。
     *              1. 没有左子树，也没有右子树，直接删除。
     *              2. 有左子树，没有右子树。 删了然后把左子树放入节点。
     *              3. 没有左子树，有右子树。删了然后把右子树放入节点。
     *              4. 有左子树，也有右子树。寻找右子树中最小的，放入节点，再把右子树最小的调用递归删掉。这时右子树最小的一定没有左子树，所以不会无线递归。简单证明如下：
     *                  简单证明，  successor 位于   root 的右子树中，因此大于   root 的所有左子节点；  successor 是   root 的右子树中的最小节点，因此小于   root 的右子树中的其他节点。以上两点保持了新子树的有序性。
     *                  在代码实现上，我们可以先寻找   successor，再删除它。  successor 是   root 的右子树中的最小节点，可以先找到   root 的右子节点，再不停地往左子节点寻找，直到找到一个不存在左子节点的节点，这个节点即为   successor。然后递归地在  root.right root.right 调用  deleteNode deleteNode 来删除   successor。因为   successor 没有左子节点，因此这一步递归调用不会再次步入这一种情况。然后将   successor 更新为新的   root 并返回。
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        return dfs(root, key);

    }

    private TreeNode dfs(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val < key) {
            return dfs(root.right, key);
        } else if (root.val > key) {
            return dfs(root.left, key);
        } else {
            // 找到了。
            return deleteRoot(root);
        }
    }

    private TreeNode deleteRoot(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null) {

        }
        // 寻找右子树最小的值。
        TreeNode minNode = root.right;
        return null;
    }
}
