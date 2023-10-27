package com.leetcode.zixue.recursive_divide;

import java.util.*;

/**
 * @author guanshida
 * @date 2023/10/17
 */
public class TempHot100 {
    /**
     * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/?envType=study-plan-v2&envId=top-100-liked
     * 17. 电话号码的字母组合
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        StringBuilder curRes = new StringBuilder();
        letterCombinations_dg(digits, 0, curRes, res);
        return res;
    }

    private void letterCombinations_dg(String digits, int pos, StringBuilder curRes, List<String> res) {
        if (pos >= digits.length() && curRes.length() != 0) {
            res.add(curRes.toString());
            return;
        }
        String s = arr[digits.charAt(pos) - '0' - 1];
        for (int i = 0; i < s.length(); i++) {
            curRes.append(s.charAt(i));
            letterCombinations_dg(digits, pos + 1, curRes, res);
            curRes.deleteCharAt(curRes.length() - 1);
        }
    }

    private static final String[] arr = new String[]{"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


    /**
     * https://leetcode.cn/problems/n-queens/?envType=study-plan-v2&envId=top-100-liked
     * 51. N 皇后
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        Set<Integer> adds = new HashSet<>();
        Set<Integer> diffs = new HashSet<>();
        boolean[][] visit = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            solveNQueens_dg(visit, 0, i, n, rows, cols, adds, diffs, res);
        }
        return res;
    }

    private void solveNQueens_dg(boolean[][] visit, int row, int col, int n, Set<Integer> rows, Set<Integer> cols, Set<Integer> adds, Set<Integer> diffs, List<List<String>> res) {
        Integer add = row + col;
        Integer diff = row - col;
        if (row >= n || col >= n || rows.contains(row) || cols.contains(col) || adds.contains(add) || diffs.contains(diff)) {
            return;
        }

        visit[row][col] = true;
        if (rows.size() >= n - 1) {
            res.add(gridToStrign(visit));
            visit[row][col] = false;
            return ;
        }

        rows.add(row);
        cols.add(col);
        adds.add(add);
        diffs.add(diff);

        // 递归下一个。
        for (int i = 0; i < n; i++) {
            solveNQueens_dg(visit, row + 1, i, n, rows, cols, adds, diffs, res);
        }


        rows.remove(row);
        cols.remove(col);
        adds.remove(add);
        diffs.remove(diff);
        visit[row][col] = false;

    }

    private List<String> gridToStrign(boolean[][] grid) {
        Arrays.toString(grid);

        List<String> list = new ArrayList<>();
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (grid[i][j]) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            list.add(sb.toString());
        }
        return list;
    }


}
