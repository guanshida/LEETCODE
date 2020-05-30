package com.leetcode.quick_union;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/friend-circles/
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。

     给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
     示例 1:

     输入:
     [[1,1,0],
     [1,1,0],
     [0,0,1]]
     输出: 2
     说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
     第2个学生自己在一个朋友圈。所以返回2。
     示例 2:

     输入:
     [[1,1,0],
     [1,1,1],
     [0,1,1]]
     输出: 1
     说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。

     注意：
         N 在[1,200]的范围内。
         对于所有学生，有M[i][i] = 1。
         如果有M[i][j] = 1，则有M[j][i] = 1。
 * @author 管世达
 * @create 2019-01-05
 **/
public class FriendCircles {
    public static void main(String[] args) {
        int[][] m = {{1,1,0}, {1,1,0}, {0,0,1}};
        FriendCircles fc = new FriendCircles();
        int circleNum1 = fc.findCircleNum2(m);
        System.out.println(circleNum1);
    }

    /**
     * 解法一：BFS
     *
     * 解法二：并查集
     * @param fs
     * @return
     */
    public int findCircleNum1(int[][] fs) {
        if (fs.length < 2) {
            return fs.length;
        }
        int count = 0;
        boolean[] person = new boolean[fs.length];
        for (int i = 0; i < person.length; i++) {
            if (!person[i]) {
                count++;
                this.BFS(fs, person, i);
            }
        }
        return count;
    }
    Queue<Integer> queue = new LinkedList<>();
    private void BFS(int[][] fs, boolean[] person, int position) {
        this.queue.clear();
        queue.add(position);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            person[index] = true;

            for (int i = 0; i < fs[index].length; i++) {
                if (fs[index][i] == 1 && !person[i]) {
                    queue.add(i);
                }
            }
        }

    }

    public int findCircleNum2(int[][] fs) {
        if (fs.length < 2) {
            return fs.length;
        }
        int n = fs.length;
        QU q = new QU(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (fs[i][j] == 0)
                    continue;
                q.union(i, j);
            }
        }

        return q.count;
    }

    class QU {
        int[] roots = null;
        int count = 0;
        QU(int n) {
            roots = new int[n];
            for (int i = 0; i < roots.length; i++) {
                roots[i] = i;
            }
            count = n;
        }

        public int find(int i) {
            int root = i;
            while (root != roots[root]) {
                root = roots[root];
            }

            if (roots[i] != root) {
                while (i != roots[i]) {
                    int tmp = roots[i];
                    roots[i] = root;
                    i = tmp;
                }
            }
            return root;
        }
        public void union(int i, int j) {
            int rooti = find(i);
            int rootj = find(j);
            if (rooti != rootj) {
                roots[rooti] = rootj;
                count--;
            }
        }
    }
}
