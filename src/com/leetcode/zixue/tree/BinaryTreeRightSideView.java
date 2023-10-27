package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=top-100-liked
 * 199. 二叉树的右视图
 * 中等
 * 相关标签
 * 相关企业
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 *
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 *
 * 输入: []
 * 输出: []
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 * 通过次数
 * 326.5K
 * 提交次数
 * 493.2K
 * 通过率
 * 66.2%
 * 请问您在哪类招聘中遇到此题？
 * 1/5
 * 社招
 * 校招
 * 实习
 * 未遇到
 * @author guanshida
 * @date 2023/10/8
 */
public class BinaryTreeRightSideView {
    /**
     * 层次遍历即可。
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int length = queue.size();
            boolean flag = false;
            for (int i = 0; i < length; i++) {
                TreeNode poll = queue.poll();
                if (!flag) {
                    res.add(poll.val);
                    flag = true;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
            }
        }
        return res;
    }
}
