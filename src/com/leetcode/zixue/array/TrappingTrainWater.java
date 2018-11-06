package com.leetcode.zixue.array;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/description/
	给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
	
	上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
	
	示例:
	输入: [0,1,0,2,1,0,1,3,2,1,2,1]
	输出: 6
 * @author windSnow
 * @date 创建时间：2018年10月25日
 *
 */
public class TrappingTrainWater {

	public static void main(String[] args) {
		int[] h = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
		TrappingTrainWater t = new TrappingTrainWater();
		int trap = t.trap(h);
		System.out.println(trap);
	}
	/**
	 * 解法一：
	 * 	Time: O(n)
	 * 	Space: O(1)
	 * 		1. 找出最大值。
	 * 		2. 从0~max遍历。
	 * 		3. 从后向max遍历
	 * @param height
	 * @return
	 */
	public int trap(int[] height) {
		if(height.length < 3) {
			return 0;
		}
		int result = 0;
		int maxIndex = 0;
		int max = height[0];
		// 1
		for (int i = 1; i < height.length; i++) {
			if(max < height[i]) {
				maxIndex = i;
				max = height[i];
			}
		}
		// 2
		int smax = 0;
		for (int i = 0; i < maxIndex; i++) {
			if(height[i] > smax) {
				smax = height[i];
			} else {
				result = result + (smax - height[i]);
			}
		}
		// 3
		smax = 0;
		for (int i = height.length - 1; i > maxIndex; i--) {
			if(height[i] > smax) {
				smax = height[i];
			} else {
				result = result + (smax - height[i]);
			}
		}
		return result;
	}
}
