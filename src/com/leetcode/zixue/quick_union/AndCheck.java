package com.leetcode.zixue.quick_union;

/**
 *  并查集工具类
 */
public class AndCheck {
    private int[] roots;

    public AndCheck(int n) {
        roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    public int findRoot(int i) {
        int root = i;
        while (root != roots[root]) {
            root = roots[root];
        }
        while (roots[i] != root) {
            int temp = roots[i];
            roots[i] = root;
            i = temp;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {
        int pRoot = findRoot(p);
        int qRoot = findRoot(q);
        if (pRoot != qRoot) {
            roots[pRoot] = qRoot;
        }
    }
}
