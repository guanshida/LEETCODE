package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
     给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

     例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]

     _______6______
     /              \
     ___2__          ___8__
     /      \        /      \
     0      _4       7       9
     /  \
     3   5

     示例 1:
     输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     输出: 6
     解释: 节点 2 和节点 8 的最近公共祖先是 6。

     示例 2:
     输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     输出: 2
     解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。

     说明:
     所有节点的值都是唯一的。
     p、q 为不同节点且均存在于给定的二叉搜索树中。
 * Created by windSnow on 2018/11/7.
 */
public class LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) {
        LowestCommonAncestorOfABinarySearchTree a = new LowestCommonAncestorOfABinarySearchTree();
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

        TreeNode t = a.lowestCommonAncestor3(n6, n2, n3);
        System.out.println(t.val);
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pList = getTreeList(root, p.val);
        List<TreeNode> qList = getTreeList(root, q.val);
        TreeNode j = null;
        int i= 0;
        for (; i < pList.size() && i < qList.size(); i++) {
            if(pList.get(i).val != qList.get(i).val) {
                j = pList.get(i - 1);
                break;
            }
        }
        i--;
        if(j == null && (pList.get(i).val == p.val || pList.get(i).val == q.val)) {
            j = pList.get(i);
        }
        return j;
    }
    public List<TreeNode> getTreeList(TreeNode root, int val) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode cur = root;
        while(cur != null) {
            list.add(cur);
            if(cur.val < val) {
                cur = cur.right;
            } else if(cur.val > val) {
                cur = cur.left;
            } else {
                break;
            }
        }
        if(cur != null) {
            return list;
        }
        return new ArrayList<>();
    }
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor2(root.left, p, q);
        }
        if(p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor2(root.right, p, q);
        }
        return root;
    }
    // lowestCommonAncestor2 的非递归版本
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        while (cur != null) {
            if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left;
            } else if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right;
            } else {
                break;
            }
        }
        return cur;
    }

}
