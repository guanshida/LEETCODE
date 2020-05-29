package com.leetcode.zixue.dp;


/**
 * https://leetcode-cn.com/problems/house-robber/
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 *
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HouseRobber {


    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        System.out.println(hr.rob1(new int[]{1, 2, 3, 1}));
        System.out.println(hr.rob1(new int[]{2,7,9,3,1}));
        System.out.println(hr.rob1(new int[]{0}));
    }
    /**
     * 解法一：动态规划
     *      Time: O(n)      Space: O(n)
     *      可以优化一下，使用几个变量也可以。
     *      Time: O(n)      Space: O(1)
     *
     *      定义dp，
     *      思想一： dp[i] 表示偷i并且前i个房屋的最大和。  dp[i] = max(dp[i - 2],dp[i - 3]) + arr[i]
     *      思想二： dp[i] 表示前i个房屋的最大和。         dp[i] = max(dp[i - 2] + arr[i], dp[i - 1])
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        } else if (nums.length == 3) {
            return Math.max(nums[0] + nums[2], nums[1]);
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = dp[0] + nums[2];
        for (int i = 3; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + nums[i];
        }
        return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
    }

    public int rob1_2(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
