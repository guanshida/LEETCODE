package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/triangle/description/
     给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     例如，给定三角形：

     [
     [2],
     [3,4],
     [6,5,7],
     [4,1,8,3]
     ]
     自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

     说明：
     如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * @author 管世达
 * @create 2018-12-17
 **/
public class Triangle {

    public static void main(String[] args) {
        List<List<Integer>> g = new ArrayList<>();
        List<Integer> g1 = new ArrayList<>();
        g1.add(2);
        g.add(g1);

        List<Integer> g2 = new ArrayList<>();
        g2.add(3);
        g2.add(4);
        g.add(g2);

        List<Integer> g3 = new ArrayList<>();
        g3.add(6);
        g3.add(5);
        g3.add(7);
        g.add(g3);

        List<Integer> g4 = new ArrayList<>();
        g4.add(4);
        g4.add(1);
        g4.add(8);
        g4.add(3);
        g.add(g4);

        Triangle a = new Triangle();
        int i = a.minimumTotal3(g);
        System.out.println(i);
    }
    /**
     * 解法一：递归
     * 解法二：递归+记忆化
     * 解法三：动态规划，递推
     *      dp方程：dp[i, j] = min(dp[i + 1, j], dp[i + 1, j + 1])   dp，二维数组
     *      Time：O(n^2)     Space: O(n^2)
     * 解法四：解法三基础上进行优化，由于只要最终结果，且dp方程中，只与i+1行有关，所以不需要是一个二维数组。
     *      Time：O(n^2)     Space: O(n)
     * @param triangle
     * @return
     */
    public int minimumTotal3(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        if (n == 1) {
            return triangle.get(0).get(0);
        }
        int[][] goal = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (i == n - 1) {
                    goal[i][j] = triangle.get(i).get(j);
                } else {
                    goal[i][j] = Math.min(goal[i + 1][j], goal[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
        }
        return goal[0][0];
    }
    public int minimumTotal4(List<List<Integer>> triangle) {

        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int n = triangle.size();
        int[] goal = new int[n + 1]; // 这里使用n+1 ，最后一个1是初始化最后一行最后一个数时，不至于下标越界
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                goal[j] = Math.min(goal[j], goal[j + 1]) + triangle.get(i).get(j);
            }
        }
        return goal[0];
    }
}
