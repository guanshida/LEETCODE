package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
     给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

     例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

        _______3______
        /              \
     ___5__          ___1__
     /      \        /      \
     6      _2       0       8
     /  \
     7   4

     示例 1:
     输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     输出: 3
     解释: 节点 5 和节点 1 的最近公共祖先是节点 3。

     示例 2:
     输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     输出: 5
     解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。

     说明:
     所有节点的值都是唯一的。
     p、q 为不同节点且均存在于给定的二叉树中。
 * Created by windSnow on 2018/11/7.
 */
public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        LowestCommonAncestorOfABinaryTree a = new LowestCommonAncestorOfABinaryTree();
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n8 = new TreeNode(8);
        TreeNode n0 = new TreeNode(0);
        TreeNode n4 = new TreeNode(4);
        TreeNode n7 = new TreeNode(7);
        TreeNode n9 = new TreeNode(9);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        n6.left = n2;
        n6.right = n8;
        n2.left = n0;
        n2.right = n4;
        n8.left = n7;
        n8.right = n9;
        n4.left = n3;
        n4.right = n5;

        TreeNode t = a.lowestCommonAncestor1(n6, n2, n9);
        System.out.println(t.val);
    }

    /**
     * 解法一： 分别计算出root到两个节点的路径。遍历两条路径。最后一个相同的节点即是最近公共祖先。（由于树无序，所以使用类似于前序遍历的递归，路径是反过来的）
     * Time: O(n)   3n
     * Space：O(n)
     * 解法二：利用树的递归遍历进行递归。对于任意一个树来说，
     * 如果root.left和root.right 都查到了节点，则最近公共祖先为root；
     * 如果有一方查到了。则最进公共祖先为查到的那一个；
     * 如果都查不到。则没有。
     * 递归终止条件是root =其中一个节点。(也是运用了递归的原理)
     * Time：O(n)   n
     * Space: O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        List<TreeNode> pList = getTreeList(root, p.val);
        List<TreeNode> qList = getTreeList(root, q.val);
        if (pList.isEmpty() || qList.isEmpty()) {
            return null;
        }

        int iq = 0;
        int ip = 0;
        if (pList.size() > qList.size()) {
            ip = pList.size() - qList.size();
        } else if (pList.size() < qList.size()) {
            iq = qList.size() - pList.size();
        }

        while (ip < pList.size()) {
            if (pList.get(ip).val == qList.get(iq).val) {
                return pList.get(ip);
            }

            ip++;
            iq++;
        }
        return null;
    }

    public List<TreeNode> getTreeList(TreeNode root, int val) {
        ArrayList<TreeNode> list = new ArrayList<>();
        boolean flag = midOrder(root, val, list);
        return list;
    }

    private boolean midOrder(TreeNode root, int val, ArrayList<TreeNode> list) {
        if (root == null) {
            return false;
        }
        if (root.val == val) {
            list.add(root);
            return true;
        }
        boolean flag = midOrder(root.left, val, list);
        if (flag) {
            list.add(root);
            return true;
        }
        flag = midOrder(root.right, val, list);
        if (flag) {
            list.add(root);
        }
        return flag;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null) {
            return null;
        }
        if(root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        if(left != null && right != null) {
            return root;
        } else if(left != null){
            return left;
        } else if(right != null){
            return right;
        }
        return null;
    }
}