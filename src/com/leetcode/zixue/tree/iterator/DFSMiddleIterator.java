package com.leetcode.zixue.tree.iterator;

import com.leetcode.tree.TreeNode;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author guanshida
 * @date 2023/3/23
 */
public class DFSMiddleIterator implements Iterator<TreeNode> {

    Stack<TreeNode> stack = new Stack<>();

    public DFSMiddleIterator(TreeNode root) {
        addStack(root);
    }

    private void addStack(TreeNode root) {
        stack.push(root);
        TreeNode pop = root;
        while (pop.left != null) {
            pop = stack.pop();
            if (pop.right != null) {
                stack.push(pop.right);
            }
            stack.push(pop);
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }
    @Override
    public boolean hasNext() {
        return !stack.empty();
    }

    @Override
    public TreeNode next() {
        if (!this.hasNext()) {
            return null;
        }
        TreeNode r = stack.peek();
        while (!stack.empty()) {
            TreeNode pop = stack.pop();
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop);
                stack.push(pop.left);
            } else {
                return pop;
            }
        }
        // 没有数据了啊
        return r;
    }
}
