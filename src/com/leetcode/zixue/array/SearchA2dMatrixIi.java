package com.leetcode.zixue.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/description/
     编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

     每行的元素从左到右升序排列。
     每列的元素从上到下升序排列。

     示例:
     现有矩阵 matrix 如下：

     [
     [1,   4,  7, 11, 15],
     [2,   5,  8, 12, 19],
     [3,   6,  9, 16, 22],
     [10, 13, 14, 17, 24],
     [18, 21, 23, 26, 30]
     ]

     给定 target = 5，返回 true。
     给定 target = 20，返回 false。
 * @author 管世达
 * @create 2018-11-28
 **/
public class SearchA2dMatrixIi {
    public static void main(String[] args) {
        int[][] arr = {{1,   4,  7, 11, 15},  {2,   5,  8, 12, 19},  {3,   6,  9, 16, 22},  {10, 13, 14, 17, 24},  {18, 21, 23, 26, 30}};
        SearchA2dMatrixIi a = new SearchA2dMatrixIi();
        boolean b = a.searchMatrix1(arr, 5);
        System.out.println(b);

    }
    public boolean searchMatrix1(int[][] matrix, int target) {
        if(matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (search(matrix[i], target) >= 0) {
                return true;
            }
        }
        return false;
    }

    //二分查找
    public int search(int[] arr, int target) {
        // 先判断边界值
        if(arr[0] > target)
            return -1;
        else if (arr[0] == target)
            return 0;
        if (arr[arr.length - 1] < target)
            return -1;
        else if (arr[arr.length - 1] == target)
            return arr.length - 1;

        //二分查找
        int start = 0;
        int end = arr.length;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if(arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        //找不到，返回最小值
        return -1;
    }
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }

        return false;
    }
    public boolean searchDg(int[][] matrix,int startRow,int endRow,int startCol,int endCol,int target) {
        if (endRow - startRow == 1) {
            return search(matrix[startRow], target) >= 0 ? true : false;
        }
        if (endCol - startCol == 1) {
            return search(matrix[startCol], target) >= 0 ? true : false;
        }



        return false;
    }

    //二分查找
    public int searchRow(int[] arr, int target) {
        // 先判断边界值
        if(arr[0] > target)
            return -1;
        else if (arr[0] == target)
            return 0;
        if (arr[arr.length - 1] < target)
            return -1;
        else if (arr[arr.length - 1] == target)
            return arr.length - 1;

        //二分查找
        int start = 0;
        int end = arr.length;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if(arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        //找不到，返回最小值
        return start;
    }

}
