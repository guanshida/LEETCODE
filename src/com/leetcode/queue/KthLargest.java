package com.leetcode.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/description/
	设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
	
	你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
	
	示例:
	
	int k = 3;
	int[] arr = [4,5,8,2];
	KthLargest kthLargest = new KthLargest(3, arr);
	kthLargest.add(3);   // returns 4
	kthLargest.add(5);   // returns 5
	kthLargest.add(10);  // returns 5
	kthLargest.add(9);   // returns 8
	kthLargest.add(4);   // returns 8
	说明: 
	你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 * @author windSnow
 * @date 创建时间：2018年10月22日
 *
 */
class KthLargest {

	public static void main(String[] args) {
		KthLargest kth = new KthLargest(3, new int[]{4,5,8,2});
		System.out.println(kth.add(3));
		System.out.println(kth.add(5));
		System.out.println(kth.add(10));
		System.out.println(kth.add(9));
		System.out.println(kth.add(4));
		
		
	}
	private PriorityQueue<Integer> queue;;
	private int k;
    public KthLargest(int k, int[] nums) {
    	queue = new PriorityQueue<>(200,new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o2 - o1;
			}
		});
        for (int i : nums) {
			queue.add(i);
		}
        this.k = k;
    }
    
    public int add(int val) {
        queue.add(val);
        Integer kth = null;
        Iterator<Integer> it = queue.iterator();
        for (int i = 1; i < k; i++) {
        	kth = it.next();
		}
        return kth;
    }
}
/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */