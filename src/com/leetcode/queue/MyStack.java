package com.leetcode.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列实现栈，解法一
 * @author windSnow
 * @date 创建时间：2018年10月19日
 *
 */
class MyStack {
	
	public static void main(String[] args) {
		 MyStack obj = new MyStack();
		 obj.push(1);
		 obj.push(2);
		 System.out.println(obj.top());
		 System.out.println(obj.pop());
		 System.out.println(obj.empty());
	}

	private Queue<Integer> q1;
	private Queue<Integer> q2;
	int top = 0;
    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new ConcurrentLinkedQueue<Integer>();
        q2 = new ConcurrentLinkedQueue<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        top = x;
        q1.add(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        
    	while(q1.size() > 1) {
    		q2.add(q1.poll());
    	}
    	while(!q2.isEmpty()) {
    		top = q2.poll();
    		q1.add(top);
    	}
    	return q1.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:

 */
