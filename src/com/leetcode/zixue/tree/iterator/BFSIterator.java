package com.leetcode.zixue.tree.iterator;

import com.leetcode.zixue.tree.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFSIterator implements Iterator<Node> {

    private Queue<Node> queue = new LinkedList<>();

    public BFSIterator(Node root) {
        if (root != null) {
            queue.add(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Node next() {
        Node poll = queue.poll();
        if (poll.left != null) {
            queue.add(poll.left);
        }
        if (poll.right != null) {
            queue.add(poll.right);
        }
        return poll;
    }
}
