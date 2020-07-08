package com.leetcode.zixue.array;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/diving-board-lcci/
 * 面试题 16.11. 跳水板
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。
 *
 * 返回的长度需要从小到大排列。
 *
 * 示例：
 *
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 提示：
 *
 * 0 < shorter <= longer
 * 0 <= k <= 100000
 * 通过次数9,790提交次数22,619
 */
public class DivingBoardLcci {

    public static void main(String[] args) {
        DivingBoardLcci a = new DivingBoardLcci();
        System.out.println(Arrays.toString(a.divingBoard(1, 1, 100)));
    }
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k <= 0) {
            return new int[0];
        }
        if (shorter == longer) {
            int[] arr = new int[1];
            arr[0] = shorter * k;
            return arr;
        }
        int[] arr = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            arr[i] = i * longer + (k - i) * shorter;
        }
        return arr;
    }
}
