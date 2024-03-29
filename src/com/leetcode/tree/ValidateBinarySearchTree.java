package com.leetcode.tree;


import java.util.ArrayList;
import java.util.List;


/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/description/

     给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     假设一个二叉搜索树具有如下特征：
         节点的左子树只包含小于当前节点的数。
         节点的右子树只包含大于当前节点的数。
         所有左子树和右子树自身必须也是二叉搜索树。
     示例 1:
         输入:
               2
             / \
           1   3
         输出: true
    示例 2:
         输入:
         5
         / \
         1   4
             / \
             3   6
     输出: false
     解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
 * Created by windSnow on 2018/11/6.
 */
public class ValidateBinarySearchTree {

    /**
     * 解法一：
     *      中序遍历，看最后的列表是不是一个从小到大的里列表。
     *      Time：O(n)
     *      Space: O(1)
     * 解法二：
     *      改造解法一，在中序列表生成的时候，可以边生成列表边进行比较。比解法一少了遍历列表的操作。
     *      Time: O(n)
     *      Space: O(n)
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        MidOrder1(root,result);
        if (result.size() < 2) {
            return true;
        }
        for (int i = 1; i < result.size(); i++) {
            if(result.get(i) <= result.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    public void MidOrder1(TreeNode root,List<Integer> list) {
        if(root == null) {
            return ;
        }
        MidOrder1(root.left,list);
        list.add(root.val);
        MidOrder1(root.right,list);
    }

    public boolean isValidBST2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        return MidOrder2(root,result);
    }
    public boolean MidOrder2(TreeNode root,List<Integer> list) {
        if(root == null) {
            return true;
        }
        boolean flag = MidOrder2(root.left,list);
        if(flag == false) {
            return false;
        }
        //比较并添加到列表
        if(!list.isEmpty() && list.get(list.size() - 1) >= root.val) {
            return false;
        }
        list.add(root.val);

        flag = MidOrder2(root.right,list);
        return flag;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode r1 = new TreeNode(1);
        TreeNode r4 = new TreeNode(10);
        TreeNode r3 = new TreeNode(4);
        TreeNode r6 = new TreeNode(12);

        root.left = r1;
        root.right = r4;
        r4.left = r3;
        r4.right = r6;
        ValidateBinarySearchTree a = new ValidateBinarySearchTree();
        boolean flag = a.isValidBST3(root);
        System.out.println(flag);

    }
    public boolean isValidBST3(TreeNode root) {
        return isValid(root,Long.MIN_VALUE  ,Long.MAX_VALUE);
    }
    public boolean isValid(TreeNode root,long min,long max) {
        if(root == null) {
            return true;
        }
        if(root.val <= min || root.val >= max)
            return false;
        return isValid(root.left,min,root.val) && isValid(root.right, root.val, max);
    }
}
