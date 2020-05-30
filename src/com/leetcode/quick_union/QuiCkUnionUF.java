package com.leetcode.quick_union;

/**
 * 并查集
 * @author 管世达
 * @create 2019-01-05
 **/
public class QuiCkUnionUF {
    private int[] roots = null;
    public QuiCkUnionUF(int n) {
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
        if (roots[i] != root) {
            while (i != roots[i]) {
                int temp = roots[i];
                roots[i] = root;
                i = temp;
            }
        }
        return root;
    }

    public boolean connected(int i, int j) {
        return this.findRoot(i) == this.findRoot(j);
    }

    public void union(int i, int j) {
        int rooti = findRoot(i);
        int rootj = findRoot(j);
        if (rooti != rootj) {
            roots[rooti] = rootj;
        }
    }
}
