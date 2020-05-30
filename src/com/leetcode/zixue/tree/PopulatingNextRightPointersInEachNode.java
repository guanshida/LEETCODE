package com.leetcode.zixue.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 管世达
 * @create 2019-03-05
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
    给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
    struct Node {
    int val;
    Node *left;
    Node *right;
    Node *next;
    }
    填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

    初始状态下，所有 next 指针都被设置为 NULL。

    示例：

    输入：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

    输出：{"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}

    解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
    提示：

    你只能使用常量级额外空间。
    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 **/
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {

        Node n13 = new Node(13);
        Node n12 = new Node(12);

        Node n11 = new Node(11, n12, n13, null);
        Node n10 = new Node(10);
        Node n9 = new Node(9,n10,n11,null);

        Node n8 = new Node(8);
        Node n7 = new Node(7);


        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n2 = new Node(2,n4,n8,null);
        Node n3 = new Node(3,n5,n7,null);
        Node n1 = new Node(1,n2,n3,null);
        PopulatingNextRightPointersInEachNode a = new PopulatingNextRightPointersInEachNode();
        Node node = a.connect1(n1);
        System.out.println(node);
    }
    /**
     * 解法一：DFS
     * 解法二：BFS
     * @param root
     * @return
     */
    public Node connect1(Node root) {
        DFS(root);
        return root;
    }

    private void DFS(Node root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            if (root.right != null) {
                root.left.next = root.right;
            } else {
                root.left.next = nextChildren(root);
            }
        }
        if (root.right != null) {
            root.right.next = nextChildren(root);

        }
        DFS(root.left);
        DFS(root.right);
    }
    private Node nextChildren(Node root) {
        Node r = root.next;
        while (r != null) {
            if (r.left != null) {
                return r.left;
            }
            if (r.right != null) {
                return r.right;
            }
            r = r.next;
        }
        return null;
    }

    public Node connect2(Node root) {
        //BFS
        Queue<Node> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Node n = queue.poll();
                if (size > 1) {
                    n.next = queue.peek();
                } else {
                    n.next = null;
                }

                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
                size--;
            }
        }

        return root;
    }
}
