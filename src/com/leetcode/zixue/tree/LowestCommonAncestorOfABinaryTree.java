package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

/**
 *
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=top-100-liked
 * 236. 二叉树的最近公共祖先
 * 已解答
 * 中等
 * 相关标签
 * 相关企业
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 *
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *
 *
 * 提示：
 *
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 * 通过次数
 * 599.5K
 * 提交次数
 * 854.7K
 * 通过率
 * 70.1%
 * @author guanshida
 * @date 2023/10/13
 */
public class LowestCommonAncestorOfABinaryTree {

    private TreeNode res = null;
    /**
     * 解法一：dfs 往下找。返回三种状态。没有、有一个、有两个。有两个时需要记录 node。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        res = null;
        dfs(root, p, q);
        return res;
    }

    private int dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return 0;
        }
        int num = 0;
        if (root == p || root == q) {
            num++;
        }
        int left = dfs(root.left, p, q);
        int right = dfs(root.right, p, q);
        if (left >= 2 || right >= 2) {
            return 2;
        } else if (left + right + num >= 2) {
            this.res = root;
            return 2;
        }
        return left + right + num;
    }
}
