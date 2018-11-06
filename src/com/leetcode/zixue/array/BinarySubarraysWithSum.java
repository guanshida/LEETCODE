package com.leetcode.zixue.array;

/**
 * https://leetcode-cn.com/contest/weekly-contest-108/problems/binary-subarrays-with-sum/
	在由若干 0 和 1  组成的数组 A 中，有多少个和为 S 的非空子数组。
	示例：
	输入：A = [1,0,1,0,1], S = 2
	输出：4
	解释：
	如下面黑体所示，有 4 个满足题目要求的子数组：
	[1,0,1,0,1]
	[1,0,1,0,1]
	[1,0,1,0,1]
	[1,0,1,0,1]
	 
	
	提示：
	A.length <= 30000
	0 <= S <= A.length
	A[i] 为 0 或 1
 * @author windSnow
 * @date 创建时间：2018年11月1日
 *
 */
public class BinarySubarraysWithSum {
	
	
	/**
	 * 
	 * @param A
	 * @param S
	 * @return
	 */
    public int numSubarraysWithSum(int[] nums, int s) {
        int sum = 0;
        int i = 0;
    	do {
    		sum += nums[i];
    		i++;
    	} while(i < nums.length && sum < s);
    	
    	if(i == nums.length && sum < s) {
    		return 0;
    	}
    	if(i == nums.length && sum == s) {
    		return 1;
    	}
    	int count = 0;
    	for ( ;i < nums.length; i++) {
			
		}
    	
    	
    	return 0;
    }
}
