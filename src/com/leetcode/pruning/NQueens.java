package com.leetcode.pruning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/n-queens/description/
     n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。


     上图为 8 皇后问题的一种解法。
     给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

     示例:

     输入: 4
     输出: [
     [".Q..",  // 解法 1
     "...Q",
     "Q...",
     "..Q."],

     ["..Q.",  // 解法 2
     "Q...",
     "...Q",
     ".Q.."]
     ]
     解释: 4 皇后问存在两个不同的解题法。
 * @author 管世达
 * @create 2018-11-27
 **/
public class NQueens {
    public static void main(String[] args) {
        NQueens a = new NQueens();
        List<List<String>> list = a.solveNQueens1(4);
        System.out.println(list);
        System.out.println(list.size());
    }

    /**
     * 解法一：自己想的递归解法，row[i] = m; 其中i = [0~n-1], m = [0~n-1]。并且m不能相同，i+m 不能相同，i-m也不能相同。则为正确答案。
     *      Time：O(n^2)     Space: O(n^2*4)
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens1(int n) {

        int[] row = new int[n];
        int[] col = new int[n];
        List<List<String>> list = new ArrayList<>();
        Set<Integer> sumSet = new HashSet<>();
        Set<Integer> subSet = new HashSet<>();
        DFS(row,col,n,0,list,sumSet,subSet);
        return list;
    }

    private void DFS(int[] row, int[] col, int n, int i, List<List<String>> list, Set<Integer> sumSet, Set<Integer> subSet) {
        if (i == n) {
            //递归结束,把row 转换成List<String>
            List<String> item = new ArrayList<>();

            char[] init = new char[n];
            Arrays.fill(init, '.');
            for (int j = 0; j < n ; j++) {
                char[] temp = Arrays.copyOf(init, n);
                temp[row[j]] = 'Q';
                item.add(new String(temp));
            }

            list.add(item);
            return;
        }
        for (int c = 0; c < n ; c++) {
            if (col[c] != 0 || sumSet.contains(i + c) || subSet.contains(i - c)) { // 已使用
                continue;
            }
            //准备数据
            col[c] = 1;
            row[i] = c;
            sumSet.add(i + c);
            subSet.add(i - c);
            //递归调用
            DFS(row, col, n, i + 1, list, sumSet, subSet);

            // 恢复现场
            col[c] = 0;
            row[i] = 0;
            sumSet.remove(i + c);
            subSet.remove(i - c);
        }
    }
}
