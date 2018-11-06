package com.leetcode.hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/description/
	给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
	你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
	
	示例:
	给定 nums = [2, 7, 11, 15], target = 9
	因为 nums[0] + nums[1] = 2 + 7 = 9
	所以返回 [0, 1]
 * @author windSnow
 * @date 创建时间：2018年10月30日
 *
 */
public class TwoSum {

	public static void main(String[] args) {
		int[] nums = new int[] {3,3};
		TwoSum a = new TwoSum();
		System.out.println(Arrays.toString(a.twoSum4(nums, 6)));
	}
	/**
	 * 解法1：暴力破解。两层循环
	 * 		Time:O(n^2)
	 * 解法2：先排序，然后再遍历一遍即可。
	 * 		Time:O(nlogn)
	 * @param nums
	 * @param target
	 * @return
	 */
    public int[] twoSum2(int[] nums, int target) {
    	if(nums.length < 2) {
    		return null;
    	}
    	Point[] points = new Point[nums.length];
    	for (int i = 0; i < nums.length; i++) {
			points[i] = new Point(nums[i],i);
		}
    	
    	Arrays.sort(points, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				return o1.i - o2.i;
			}
		});
    	
    	int i = 0,j = nums.length - 1;
    	while (j >= i) {
			if(points[i].i + points[j].i > target) {
				j --;
			} else if(points[i].i + points[j].i < target) {
				i ++;
			} else {
				break;
			}
		}
    	int[] result = new int[]{Math.min(points[i].index, points[j].index),Math.max(points[i].index, points[j].index)};
        return result;
    }
    class Point {
    	int i;
    	int index;
		public Point(int i, int index) {
			super();
			this.i = i;
			this.index = index;
		}
    	
    }
    
    /**
     * 解法3：使用hash。对于每一个值，只需要看有没有target-nums[i] 的值。
     * 		Time:O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
    	if(nums.length < 2) {
    		return null;
    	}
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}
    	int[] result = null;
    	for (int i = 0; i < nums.length; i++) {
    		Integer en = Integer.valueOf(target - nums[i]);
    		if(map.containsKey(en) && map.get(en) != i) {
		    	result = new int[]{i,map.get(en)};
		    	break;
			}
		}
        return result;
    }
    public int[] twoSum4(int[] nums, int target) {
    	if(nums.length < 2) {
    		return null;
    	}
    	int[] result = null;
    	Map<Integer, Integer> map = new HashMap<>();
    	for (int i = 0; i < nums.length; i++) {
    		Integer en = Integer.valueOf(target - nums[i]);
    		if(map.containsKey(en)) {
    			result = new int[]{map.get(en),i};
    			break;
    		}
    		map.put(nums[i],i);
    	}
    	return result;
    }
   
}
