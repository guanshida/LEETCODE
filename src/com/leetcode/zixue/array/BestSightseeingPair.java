package com.leetcode.zixue.array;

/**
 * https://leetcode-cn.com/problems/best-sightseeing-pair/
 *
 * 1014. 最佳观光组合
 *
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 *
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 *
 * 返回一对观光景点能取得的最高分。
 *
 *  
 *
 * 示例：
 *
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *  
 *
 * 提示：
 *
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 * 通过次数7,811提交次数15,584
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BestSightseeingPair {

    public static void main(String[] args) {
        BestSightseeingPair a = new BestSightseeingPair();
        System.out.println(a.maxScoreSightseeingPair4(new int[]{8, 1, 5, 2, 6}));

        System.out.println(a.maxScoreSightseeingPair5(new int[]{8, 1, 5, 2, 6}));

    }

    /**
     * 解法一： 暴力求解：直接循环两遍，路程中存储最大值。
     *          Time: O(n^2）        Space: O(1)
     *
     * 以下解法基于：
     *      可以先计算出 arr[j] - j 组成一个新的数组 temp[] 。那么 当固定i时（i<j），有 A[i] + A[j] - (j - i) = A[i] + i + temp[j]
     * 解法二： 先计算出 temp  的数组。然后把该数组全部放入大底堆。遍历一遍时把自己从大底堆删除即可求得最大值。
     *          Time: O(nlogn)      Space: O(n)
     *
     * 解法三： 先计算出 temp  的数组。然后把temp数组排序，遍历i时，从右到左遍历，遍历出第一个数的下标j>i时，即使固定了i时的最大值。
     *          Time: O(nlogn)      Space: O(n)     因为从右到左遍历temp时，如果碰到了合法的j值就直接是最大值，碰不到就从数组中移除。这样碰不到时最多也就遍历一次。可以近似为O(1)，所以最大的时间复杂度为排序时的O(nlogn)
     *
     * 解法四： 当遍历i时，可以看出只需要查出一个j，使的 j>i 时求出temp的最大值，也就是说，求出temp数组下标为i之后的最大的值（和单调栈类似），然后把 “下标为i之后的最大的值” 再拼装成一个数组
     *      求出“下标为i之后的最大的值”的数组，有两种方法：
     *          1. 从右往左遍历temp数组，维护最大值，即可求出。
     *          2. 动态规划，定义状态为：dp[i] 表示从索引i之后(不包括索引i)最大的值。那么状态转移方程为：  dp[i] = max(dp[i+1], dp[i+1])
     *      。这样在遍历i时，只需要O(1) 的时间复杂度即可确定。
     *          Time: O(n)          Space: O(n)
     *
     * 解法五： 反过来思考，以上四种解法都是保存i不动，寻找j。现在我们考虑j不动，寻找i试一试。也就是说当j不动时，只需要找出 arr[0 ~ j-1] 中 arr[i]+i 的最大值即可。一边遍历，一边维护最大值即可。
     *          Time: O(n)          Space: O(1)
     *
     * @param arr
     * @return
     */
    public int maxScoreSightseeingPair(int[] arr) {

        return 0;

    }


    public int maxScoreSightseeingPair4(int[] arr) {

        if (arr.length < 2) {
            return 0;
        }
        if (arr.length == 2) {
            return arr[0] + arr[1] - 1;
        }

        // arr.length>2
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i] - i;
        }

        int[] maxArr = new int[arr.length];
        int maxIndex = temp.length - 1;
        for (int i = temp.length - 1; i >= 0; i--) {
            maxArr[i] = temp[maxIndex];
            if (temp[maxIndex] < temp[i]) {
                maxIndex = i;
            }
        }
        int max = 0;
        for (int i = 0; i < maxArr.length - 1; i++) {
            int t = arr[i] + i + maxArr[i];
            if (max < t) {
                max = t;
            }
        }
        return max;
    }

    public int maxScoreSightseeingPair5(int[] arr) {

        if (arr.length < 2) {
            return 0;
        }

        // arr.length>2
        int max = -1;
        int mx = arr[0];
        for (int j = 1; j < arr.length; j++) {
            max = Math.max(mx + arr[j] - j, max);
            mx = Math.max(mx, arr[j] + j);
        }
        return max;
    }
}
