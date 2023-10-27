package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @author guanshida
 * @date 2023/9/25
 */
public class TempTest {

    /**
     * https://leetcode.cn/problems/invert-binary-tree/?envType=study-plan-v2&envId=top-100-liked
     * 226. 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    }

    /**
     * https://leetcode.cn/problems/symmetric-tree/?envType=study-plan-v2&envId=top-100-liked
     * 101. 对称二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return dfs_isSymmetric(root,root);
    }

    private boolean dfs_isSymmetric(TreeNode root, TreeNode mirror) {
        if (root == null && mirror == null) {
            return true;
        }
        if (root == null || mirror == null) {
            return false;
        }
        return root.val == mirror.val && dfs_isSymmetric(root.left, mirror.right) && dfs_isSymmetric(root.right, mirror.left);
    }


    /**
     * https://leetcode.cn/problems/diameter-of-binary-tree/?envType=study-plan-v2&envId=top-100-liked
     * 543. 二叉树的直径
     */
    private int maxPath;
    public int diameterOfBinaryTree(TreeNode root) {
        maxPath = 0;
        diameterOfBinaryTree_dfs(root);
        return maxPath-1;
    }

    private int diameterOfBinaryTree_dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = diameterOfBinaryTree_dfs(root.left);
        int right = diameterOfBinaryTree_dfs(root.right);
        maxPath = Math.max(left + right + 1, maxPath);
        return Math.max(left, right) + 1;
    }

    /**
     * https://leetcode.cn/problems/binary-tree-level-order-traversal/?envType=study-plan-v2&envId=top-100-liked
     * 102. 二叉树的层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/validate-binary-search-tree/?envType=study-plan-v2&envId=top-100-liked
     * 98. 验证二叉搜索树
     * 错误的解答。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }

        if (root.left != null && root.left.val >= root.val) {
            return false;
        }
        if (root.right != null && root.right.val <= root.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     *https://leetcode.cn/problems/kth-smallest-element-in-a-bst/?envType=study-plan-v2&envId=top-100-liked
     * 230. 二叉搜索树中第K小的元素
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<Integer> stack = new Stack<>();
         kthSmallest_dfs(root, k, stack);
        return stack.peek();
    }

    private void kthSmallest_dfs(TreeNode root, int k, Stack<Integer> stack) {
        if (stack.size() >= k || root == null) {
            return;
        }
        kthSmallest_dfs(root.left, k, stack);
        if (stack.size() < k) {
            stack.push(root.val);
            if (stack.size() < k) {
                kthSmallest_dfs(root.right, k, stack);
            }
        }
    }

    /**
     * https://leetcode.cn/problems/path-sum-iii/description/?envType=study-plan-v2&envId=top-100-liked
     * 437. 路径总和 III
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {

        return pathSum_dfs(root, targetSum, new ArrayList<Long>());
    }

    @Test
    public void test_pathSum() {
        TreeNode root = TreeNode.valueOf(new Integer[]{10, 5, -3, 3, 2, null, 11, 3, -2, null, 1});
        System.out.println(pathSum(root, 8));
    }
    private int pathSum_dfs(TreeNode root, int targetSum, List<Long> list) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + root.val);
            if (list.get(i) == targetSum) {
                count++;
            }
        }
        list.add((long) root.val);
        if (root.val == targetSum) {
            count++;
        }
        int leftCount = pathSum_dfs(root.left, targetSum, list);
        int rightCount = pathSum_dfs(root.right, targetSum, list);
        count = count + leftCount + rightCount;

        list.remove(list.size() - 1);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) - root.val);
        }
        return count;
    }

    /**
     * https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/?envType=study-plan-v2&envId=top-100-liked
     * 124. 二叉树中的最大路径和
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        maxPathSum_dfs(root);
        return max;
    }
    private int max = Integer.MIN_VALUE;
    private int maxPathSum_dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = maxPathSum_dfs(root.left);
        int rightMax = maxPathSum_dfs(root.right);
        max = Math.max(max, leftMax + root.val);
        max = Math.max(max, rightMax + root.val);
        max = Math.max(max, root.val);
        max = Math.max(max, leftMax + rightMax + root.val);
        return Math.max(Math.max(leftMax, rightMax), 0) + root.val;
    }

}
