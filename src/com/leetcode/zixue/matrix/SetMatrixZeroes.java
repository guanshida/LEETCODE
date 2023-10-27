package com.leetcode.zixue.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/set-matrix-zeroes/description/?envType=study-plan-v2&envId=top-100-liked
 * 73. 矩阵置零
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 * 示例 2：
 *
 *
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 *
 *
 * 进阶：
 *
 * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 * 通过次数
 * 275.6K
 * 提交次数
 * 431.4K
 * 通过率
 * 63.9%
 * @author guanshida
 * @date 2023/9/18
 */
public class SetMatrixZeroes {
    public static void main(String[] args) {


    }


    /**
     * 解法一：先遍历一遍，把需要置零的行和列存储下来，然后再遍历一遍进行置 0；
     *      Space:O(n+m)            Time:O(m*n)         m为行，n 位列。
     *
     * 解法二：
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        boolean[] rowZeroSet = new boolean[rowLen];
        boolean[] colZeroSet = new boolean[colLen];

        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (matrix[i][j] == 0) {
                    colZeroSet[j] = true;
                    rowZeroSet[i] = true;
                }
            }
        }
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (matrix[i][j] != 0 && (rowZeroSet[i] || colZeroSet[j])) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
