package com.leetcode.zixue.matrix;

/**
 * https://leetcode.cn/problems/rotate-image/?envType=study-plan-v2&envId=top-100-liked
 * 48. 旋转图像
 * 中等
 * 相关标签
 * 相关企业
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 * 示例 2：
 *
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 *
 * 提示：
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 *
 * 通过次数
 * 485.4K
 * 提交次数
 * 647.4K
 * 通过率
 * 75.0%
 * @author guanshida
 * @date 2023/9/18
 */
public class RotateImage {

    public static void main(String[] args) {

    }

    /**
     * 解法一：搞一个新的数组，转换后的搞到新数组里，然后赋值一下即可。
     *          Space: O(m*n)           Time:O(m*n)
     * 解法二：直接原地修改，但需要记录一个元素是否被修改过。
     *          Space:O(m*n)            Time:O(m*n)
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;


        boolean[][] visit = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visit[i][j]) {
                    continue;
                }
                int tmp = matrix[i][j];
                // 计算转移的位置

                //备份转移的数据。
                // 转移
            }
        }
    }
}
