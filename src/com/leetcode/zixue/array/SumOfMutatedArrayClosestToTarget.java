package com.leetcode.zixue.array;

import java.util.Arrays;

/**
 *
 * https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/
 *
 * 1300. 转变数组后最接近目标值的数组和
 *
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近  target （最接近表示两者之差的绝对值最小）。

 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。

 请注意，答案不一定是 arr 中的数字。

  

 示例 1：

 输入：arr = [4,9,3], target = 10
 输出：3
 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 示例 2：

 输入：arr = [2,3,5], target = 10
 输出：5
 示例 3：

 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 输出：11361
  

 提示：

 1 <= arr.length <= 10^4
 1 <= arr[i], target <= 10^5
 通过次数3,557提交次数7,600

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 管世达
 * @create 2020-06-14
 **/
public class SumOfMutatedArrayClosestToTarget {


    public static void main(String[] args) {
        SumOfMutatedArrayClosestToTarget a = new SumOfMutatedArrayClosestToTarget();
        System.out.println(a.findBestValue2(new int[]{4, 9, 3}, 10));
        System.out.println(a.findBestValue2(new int[]{2, 3, 5}, 10));
        System.out.println(a.findBestValue2(new int[]{60864, 25176, 27249, 21296, 20204}, 56803));
        System.out.println(a.findBestValue2(new int[]{1547, 83230, 57084, 93444, 70879}, 71237));

        System.out.println(a.findBestValue4(new int[]{4, 9, 3}, 10));
        System.out.println(a.findBestValue4(new int[]{2, 3, 5}, 10));
        System.out.println(a.findBestValue4(new int[]{60864, 25176, 27249, 21296, 20204}, 56803));
        System.out.println(a.findBestValue4(new int[]{1547, 83230, 57084, 93444, 70879}, 71237));

    }

    /**
     * 解法一：暴力破解：从1到最大值每个都试一遍，求出值。
     *          Time: O(n*max(arr[i]))        Space: O(1)
     *
     * 解法二：二分查找。从min到max进行二分查询。但要去掉临界值。
     *          Time: O(n*log(max(arr[i])))     Space: O(1)
     *
     * 先排序时。因为排序后可以先算出前缀和，这样在计算sum（数组求和）时只需要O(1)(知道索引情况下)或者O(logn)（不知道索引，只知道值情况下，查出索引的复杂度为logn）的时间复杂度。
     *
     * 解法三： 先排序，然后在[1,max(arr)]中使用二分查找。
     *          Time: O(nlogn + log(max(arr)))              Space: O(n)
     *
     * 解法四： 先排序，然后先用二分求出在那两个元素之间，再在两个元素之间使用二分查找。
     *          Time: O(nlogn + log(两元素之差))              Space: O(n)
     *
     * @param arr
     * @param target
     * @return
     */
    public int findBestValue2(int[] arr, int target) {

        if (arr.length == 1) {
            return arr[0] > target ? target : arr[0];
        }

        int max = arr[0];
        int min = arr[0];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        if (sum <= target) {
            return max;
        }
        int avg = target / arr.length;
        if (min > avg) {
            return Math.abs(avg * arr.length - target) <= Math.abs((avg + 1) * arr.length - target) ? avg : avg + 1;
        }

        int left = min,right = max,mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int temp = this.sumCondition(arr, mid);
            if (temp < target) {
                left = mid + 1;
            } else if (temp > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }

        return Math.abs(this.sumCondition(arr, left) - target) >= Math.abs(this.sumCondition(arr, right) - target) ? right : left;
    }

    private int sumCondition(int[] arr, int mid) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] > mid ? mid : arr[i];
        }
        return sum;
    }

    public int findBestValue4(int[] arr, int target) {
        Arrays.sort(arr);

        if (arr.length == 1) {
            return arr[0] > target ? target : arr[0];
        }

        int[] prefix = new int[arr.length];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            prefix[i] = sum;
        }

        if (sum <= target) {
            return arr[arr.length - 1];
        }
        int avg = target / arr.length;
        if (arr[0] >= avg) {
            return Math.abs(avg * arr.length - target) <= Math.abs((avg + 1) * arr.length - target) ? avg : avg + 1;
        }

        int left = 0,right = arr.length - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int temp = this.sumCondition2(arr, mid, arr[mid], prefix);
            if (temp < target) {
                left = mid + 1;
            } else if (temp > target) {
                right = mid - 1;
            } else {
                return arr[mid];
            }
        }
        // 求的值在 (arr[right],arr[left]) 之间。再次使用二分查找。
        int index = right;
        int left2 = arr[right], right2 = arr[left];
        while (left2 <= right2) {
            mid = left2 + (right2 - left2) / 2;
            int temp = this.sumCondition2(arr, index, mid, prefix);
            if (temp < target) {
                left2 = mid + 1;
            } else if (temp > target) {
                right2 = mid - 1;
            } else {
                return mid;
            }
        }

        return Math.abs(this.sumCondition2(arr, index, left2, prefix) - target) >= Math.abs(this.sumCondition2(arr, index, right2, prefix) - target) ? right2 : left2;
    }

    private int sumCondition2(int[] arr, int targetIndex,int target, int[] prefix) {

        return prefix[targetIndex] + (arr.length - targetIndex - 1) * target;
    }
}
