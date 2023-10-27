package com.leetcode.zixue.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 * 通过次数336,379提交次数682,361
 *
 * @author guanshida
 * @date 2023/1/29
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        boolean[][] visits = new boolean[rowNum][colNum];
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        List<Integer> result = new ArrayList<>(rowNum * colNum);
        int row = 0, col = 0;
        int directionIndex = 0;
        for (int i = 0; i < rowNum * colNum; i++) {
            result.add(matrix[row][col]);
            visits[row][col] = true;
            int nextRow = row + directions[directionIndex][0];
            int nextCol = col + directions[directionIndex][1];
            if (nextRow < 0 || nextCol < 0 || nextRow >= rowNum || nextCol >= colNum || visits[nextRow][nextCol]) {
                directionIndex = (directionIndex + 1) % 4;
            }
            row += directions[directionIndex][0];
            col += directions[directionIndex][1];
        }

        return result;
    }


    /**
     * 解法一：
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder2(int[][] matrix) {

        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int[] rows = new int[]{0, 1, 0, -1};
        int[] cols = new int[]{1, 0, -1, 0};
        int minCol = -1, maxCol = colLen;
        int minRow = -1, maxRow = rowLen;
        List<Integer> res = new ArrayList<>(rowLen * colLen);

        int curRow = 0;
        int curCol = 0;
        int i = 0;
        while (res.size() < rowLen * colLen){
            if (curRow > minRow && curRow < maxRow && curCol > minCol && curCol < maxCol) {
                res.add(matrix[curRow][curCol]);
                curCol += cols[i];
                curRow += rows[i];
            } else {
                if (i == 0) {
                    minRow++;
                } else if (i == 1) {
                    maxCol--;
                } else if (i == 2) {
                    maxRow--;
                } else {
                    minCol++;
                }
                curCol -= cols[i];
                curRow -= rows[i];
                i = (i + 1) % rows.length;
                curCol += cols[i];
                curRow += rows[i];
            }
        }
        return res;
    }
}
