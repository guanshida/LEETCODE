package com.leetcode.zixue.binary_search;


/**
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * <p>
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * @author 管世达
 * @create 2018-11-14 20:33
 **/
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5};
        SearchInRotatedSortedArray a = new SearchInRotatedSortedArray();
        int search = a.search(nums, 3);
        System.out.println(search);
    }

    /**
     * 解法一：先排序，再查找。
     * Sort排序：Time: O(nlogn)       Space: O(1)
     * 旋转1：    Time: O(n)           Space:(1)
     * 解法二：找出旋转的节点。然后判断目标数再左面还是右面。然后使用二分查找。
     * Time: O(logn)       Space: O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        //找出旋转的节点。
        if (nums.length < 1) {
            return -1;
        } else if (nums.length == 1 && target == nums[0]) {
            return 0;
        } else if (nums.length == 1 && target != nums[0]){
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int temp = 0;
        while (right - left > 1) {
            temp = (right + left) / 2;
            if (nums[left] > nums[temp]) {
                right = temp;
            } else if (nums[temp] > nums[right]) {
                left = temp;
            } else {
                //没有旋转。正常排序
                right = 0;
                break;
            }
        }
        if (nums[0] <= target && target <= nums[left]) {
            return binarySearch(nums, 0, left, target);
        }
        if (nums[right] <= target && target <= nums[nums.length - 1]) {
            return binarySearch(nums, right, nums.length - 1, target);
        }
        return -1;
    }

    public int binarySearch(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int ret = -1;
        int mid;
        while (end >= start) {
            mid = start + (end - start) / 2;//直接平均可能會溢位，所以用此算法
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target == nums[mid]) {
                ret = mid;
                break;
            } else {
                start = mid + 1;
            }
        }
        return ret;
    }
}
