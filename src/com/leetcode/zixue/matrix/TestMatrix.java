package com.leetcode.zixue.matrix;

/**
 * @author guanshida
 * @date 2023/9/21
 */
public class TestMatrix {


    /**
     * https://leetcode.cn/problems/search-a-2d-matrix-ii/?envType=study-plan-v2&envId=top-100-liked
     * 解法一：横着一个二分。遍历每一行
     *              Space:O(1)              Time:O(nlogm)
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
//        searchBinary(matrix, target);
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] > target || target > matrix[i][cols - 1]) {
                continue;
            }
            int left = 0;
            int right = cols - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (matrix[i][mid] > target) {
                    right = mid - 1;
                } else if (matrix[i][mid] < target) {
                    left = mid + 1;
                } else {
                    return true;
                }
            }
        }
        return false;

    }
}
