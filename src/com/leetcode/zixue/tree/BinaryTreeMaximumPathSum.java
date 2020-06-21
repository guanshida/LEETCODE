package com.leetcode.zixue.tree;

import com.leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 *
 *124. 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。

 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

 示例 1:

 输入: [1,2,3]

 1
 / \
 2   3

 输出: 6
 示例 2:

 输入: [-10,9,20,null,null,15,7]

    -10
    / \
   9  20
     /  \
    15   7

 输出: 42
 通过次数46,242提交次数112,116

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 管世达
 * @create 2020-06-21
 **/
public class BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree s = new SerializeAndDeserializeBinaryTree();
        BinaryTreeMaximumPathSum a = new BinaryTreeMaximumPathSum();

        System.out.println("解法一");
        System.out.println(a.maxPathSum1(s.deserialize1_2("-2,-1,null,null,null,null")));
        System.out.println(a.maxPathSum1(s.deserialize1_2("-10,null,null")));
        System.out.println(a.maxPathSum1(s.deserialize1_2("-10,9,20,null,null,15,7")));

        System.out.println("解法二");
        System.out.println(a.maxPathSum2(s.deserialize1_2("-2,-1,null,null,null,null")));
        System.out.println(a.maxPathSum2(s.deserialize1_2("-10,null,null")));
        System.out.println(a.maxPathSum2(s.deserialize1_2("-10,9,20,null,null,15,7")));
    }

    /**
     * 解法一： dp + DFS：定义 dp[i] 为：当某一个节点为最大路径的根节点（左子树最大值+root节点+右子树最大值）时，最大的路径。然后使用DFS
     * 把每个路径轮一遍，取最大值，即可。由于本题目只需要求出最大的值，所以不需要把i和某个node节点对应起来。
     *          Time: O(n^2)              Space: O(n)
     *      优化一下：可以把每一个节点的左子树最大值和右子树最大值缓存起来，这样就不需要第二层递归了，
     *          Time: O(n)              Space: O(n)             因为每个节点最多遍历两次，（一次求单路径最大值，一个求多路径最大值），所以时间复杂度为 O(n)
     *
     * 解法二： 思想差不多一样。只是代码少遍历了一次。
     *      边DFS遍历，边求出最大值，解法一中不能一次的原因是因为一个得返回两条路径最大值，一个返回一条路径最大值。冲突了。此解法：只返回一条路径最大值。两条路径最大值使用类变量存储。
     *          Time: O(n)              Space: O(n)
     *
     * @param root
     * @return
     */
    private Map<TreeNode, Integer> map = new HashMap<>();
    public int maxPathSum1(TreeNode root) {
        return diguiDFS(root);
    }

    private int diguiDFS(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int value = root.val + maxPath(root.left) + maxPath(root.right);

        int max = Math.max(diguiDFS(root.left), diguiDFS(root.right));
        return max != 0 ? Math.max(max, value) : value;
    }

    private int maxPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        int max = Math.max(maxPath(root.left), maxPath(root.right));
        if (max < 0) {
            max = 0;
        }
        max = Math.max(max + root.val, 0);
        map.put(root, max);
        return max;
    }

    private int max = Integer.MIN_VALUE;
    public int maxPathSum2(TreeNode root) {
        max = Integer.MIN_VALUE;
        diguiDFS2(root);
        return max;
    }

    private int diguiDFS2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(diguiDFS2(root.left), 0);
        int right = Math.max(diguiDFS2(root.right), 0);
        int value = root.val + left + right;
        max = Math.max(max, value);
        return Math.max(left, right) + root.val;
    }
}
