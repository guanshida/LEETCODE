package com.leetcode.zixue.array;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        MedianOfTwoSortedArrays a = new MedianOfTwoSortedArrays();
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        double v = a.findMedianSortedArrays2_1(nums1, nums2);
        System.out.println(v);
    }
    /**
     * 1. 先排序，再求值。
     *      a. 直接使用sort排序：  Time: O((n+m)*log(n+m))     Space: O(n+m)
     *      b. 一趟排序：        Time: O(n+m)            Space: O(n+m)
     * 2. 优化：排到中间，直接求值。
     *          Time: O(n+m)        Space: O(1)
     * 3. 先使用二分法选取num1中的一个值a，在num2中使用二分法找出最大的小于a的那个值b，判断a或b是不是中间的数。如果是，直接返回，否则，循环。
     *          Time: O(logn * logm) = O(log(n+m))      Space: O(1)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0) {
            return (nums2.length & 1) == 1 ? nums2[nums2.length / 2] : (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0;
        }
        if (nums2 == null || nums2.length == 0) {
            return (nums1.length & 1) == 1 ? nums1[nums1.length / 2] : (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2.0;
        }

        int totalLength = nums1.length + nums2.length;
        int mid = totalLength % 2 == 0 ? totalLength / 2 - 1 : totalLength / 2;
        int count = 0;

        int cur1 = 0, cur2 = 0;
        int cur = 0;
        while (cur1 < nums1.length && cur2 < nums2.length && count <= mid) {
            if (nums1[cur1] < nums2[cur2]) {
                cur = nums1[cur1];
                cur1++;
            } else {
                cur = nums2[cur2];
                cur2++;
            }
            count ++;
        }
        while (cur1 < nums1.length && count <= mid) {

            cur = nums1[cur1];
            cur1++;
            count++;
        }
        while (cur2 < nums2.length && count <= mid) {

            cur = nums2[cur2];
            cur2++;
            count++;
        }
        if ((totalLength & 1) == 1) {
            return cur;
        } else {
            int n = 0;
            if (cur1 >= nums1.length) {
                n = nums2[cur2];
            } else if (cur2 >= nums2.length) {
                n = nums1[cur1];
            } else {
                n = nums1[cur1] < nums2[cur2] ? nums1[cur1] : nums2[cur2];
            }

            return (cur + n) / 2.0;
        }
    }

    public double findMedianSortedArrays2_1(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length == 0) {
            return (nums2.length & 1) == 1 ? nums2[nums2.length / 2] : (nums2[nums2.length / 2] + nums2[nums2.length / 2 - 1]) / 2.0;
        }
        if (nums2 == null || nums2.length == 0) {
            return (nums1.length & 1) == 1 ? nums1[nums1.length / 2] : (nums1[nums1.length / 2] + nums1[nums1.length / 2 - 1]) / 2.0;
        }

        int totalLength = nums1.length + nums2.length;
        int mid = totalLength / 2;
        int count = 0;

        int cur1 = 0, cur2 = 0;
        int cur = -1, prev = -1;
        while (count <= mid) {
            prev = cur;
            if (cur1 < nums1.length && (cur2 >= nums2.length || nums1[cur1] < nums2[cur2])) {
                cur = nums1[cur1++];
            } else {
                cur = nums2[cur2++];
            }
            count ++;
        }
        if ((totalLength & 1) == 1) {
            return cur;
        } else {
            return (cur + prev) / 2.0;
        }
    }

}
