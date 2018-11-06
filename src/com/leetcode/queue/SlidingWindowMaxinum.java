package com.leetcode.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * sliding-window  高频。容易面试的题目。    如果只需要一个值，比如说最大值、最小值。保底优先队列，最好考虑返璞归真。（队列，双端队列）
 * https://leetcode-cn.com/problems/sliding-window-maximum/description/
	给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
	返回滑动窗口最大值。
	
	示例:
	输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
	输出: [3,3,5,5,6,7] 
	
	解释: 
	  滑动窗口的位置                最大值
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	注意：
	你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
	
	进阶：
	你能在线性时间复杂度内解决此题吗？
 * @author windSnow
 * @date 创建时间：2018年10月22日
 *
 */
public class SlidingWindowMaxinum {
	
	public static void main(String[] args) {
		SlidingWindowMaxinum s = new SlidingWindowMaxinum();
		int[] r = s.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
		System.out.println(Arrays.toString(r));
	}
	
	
	/**
	 * Time : 
	 * Space : O(k)
	 * 双端队列：LinkedList / ArrayDeque
	 * @param nums
	 * @param k
	 * @return
	 */
    public int[] maxSlidingWindow2(int[] nums, int k) {
    	if(nums.length == 0 || nums.length == 1) {
    		return nums;
    	}
        int[] result = new int[nums.length - k + 1];
        
        return result;
    }

	
	/**
	 * Time : O(nlogk)
	 * Space : O(k)
	 * 优先队列。
	 * @param nums
	 * @param k
	 * @return
	 */
    public int[] maxSlidingWindow(int[] nums, int k) {
    	if(nums.length == 0) {
    		return new int[0];
    	}
        int[] result = new int[nums.length - k + 1];
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2 - o1;
			}
		});
        for (int i = 0; i < k; i++) {
			queue.add(nums[i]);
		}
        result[0] = queue.peek();
        for (int i = k; i < nums.length; i++) {
			queue.add(nums[i]);
			queue.remove(nums[i - k]);
			result[i - k + 1] = queue.peek();
		}
        return result;
    }
}
