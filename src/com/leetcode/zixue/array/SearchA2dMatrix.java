package com.leetcode.zixue.array;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix/
 *
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *
 *
 * 提示：
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 * 通过次数300,018提交次数618,001
 * 请问您在哪类招聘中遇到此题？
 *
 * @author guanshida
 * @date 2023/3/22
 */
public class SearchA2dMatrix {

    public static void main(String[] args) {
        SearchA2dMatrix a = new SearchA2dMatrix();
        System.out.println(a.searchMatrix(new int[][]{{1, 3}}, 3));
    }

    /**
     * 解法一：看成一个大数组，然后进行二分查找。下标映射一下就行。
     *
     * 解法二：横竖两次二分查找。注意：最好先竖再横。因为这样
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length < 1) {
            return false;
        }
        int row = lowSearch(matrix, target);
        if (row < 0) {
            return false;
        }
        return binarySearch(matrix[row], target);
    }

    private boolean binarySearch(int[] arr, int target) {
        if (arr.length <= 0) {
            return false;
        }
        int l = 0, r = arr.length - 1;
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    private int lowSearch(int[][] matrix, int target) {
        if (matrix.length <= 0) {
            return -1;
        }
        int l = 0,r = matrix.length - 1;
        int mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (matrix[mid][0] == target) {
                return mid;
            } else if (matrix[mid][0] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
}
