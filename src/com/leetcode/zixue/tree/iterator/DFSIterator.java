package com.leetcode.zixue.tree.iterator;

import com.leetcode.zixue.tree.Node;

import java.util.Iterator;
import java.util.Stack;

public class DFSIterator implements Iterator<Node> {

    private Stack<Node> stack = new Stack<>();

    public DFSIterator(Node root) {
        if (root != null) {
            stack.push(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node next() {
        Node pop = stack.pop();
        if (pop.right != null) {
            stack.push(pop.right);
        }
        if (pop.left != null) {
            stack.push(pop.left);
        }
        return pop;
    }
}
