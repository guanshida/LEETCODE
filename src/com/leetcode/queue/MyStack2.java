package com.leetcode.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列实现栈，解法二
 * @author windSnow
 * @date 创建时间：2018年10月19日
 *
 */
class MyStack2 {
	
	public static void main(String[] args) {
		 MyStack2 obj = new MyStack2();
		 obj.push(1);
		 obj.push(2);
		 System.out.println(obj.top());
		 System.out.println(obj.pop());
		 System.out.println(obj.empty());
	}

	private Queue<Integer> queue;
	private Queue<Integer> temp;
    /** Initialize your data structure here. */
    public MyStack2() {
    	queue = new ConcurrentLinkedQueue<Integer>();
    	temp = new ConcurrentLinkedQueue<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
    	if(queue.isEmpty()) {
    		queue.add(x);
    		return;
    	}
    	temp.add(x);
        while(!queue.isEmpty()) {
        	temp.add(queue.poll());
        }
        Queue<Integer> q = queue;
        queue = temp;
        temp = q;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    	return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:

 */
