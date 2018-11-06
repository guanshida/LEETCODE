package com.leetcode.zixue.array;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/description/
	给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
	说明：你不能倾斜容器，且 n 的值至少为 2。
	
	图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
	示例:
	输入: [1,8,6,2,5,4,8,3,7]
	输出: 49
 * @author windSnow
 * @date 创建时间：2018年10月25日
 *
 */
public class ContainerWithMostWater {
	public static void main(String[] args) {
		int[] a = new int[]{1,8,6,2,5,4,8,3,7};
		ContainerWithMostWater c = new ContainerWithMostWater();
		System.out.println(c.maxArea(a));
	}
	/**
	 * 任意a[i] ,a[j]的存水量为   |i-j| * min(a[i],a[j])
	 * 解法1：
	 * 	Time：O(n^2)
	 * 	Space: O(1)
	 * 	两两计算出存水量。然后求存水量的最大值。
	 * 
	 * 解法2：
	 * 	双指针法。
	 * @param height
	 * @return
	 */
    public int maxArea(int[] height) {
        //解法2
    	int i = 0,j = height.length - 1;
    	int max = 0;
    	while(i < j) {
    		max = Math.max((j - i) * Math.min(height[i], height[j]),max);
    		if(height[i] > height[j]) {
    			j -- ;
    		} else {
    			i ++;
    		}
    	}
    	
    	return max;
    }
}
