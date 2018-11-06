package com.leetcode.stack;

import java.util.Stack;

public class MyQueue2 {

	public static void main(String[] args) {
		MyQueue2 queue = new MyQueue2();

		queue.push(1);
//		queue.push(2);  
		System.out.println(queue.peek());  // 返回 1
		System.out.println(queue.pop());   // 返回 1
		System.out.println(queue.empty()); // 返回 false	
	}
	private Stack<Integer> s1;
	private Stack<Integer> s2;
	int front = 0;
    /** Initialize your data structure here. */
    public MyQueue2() {
    	s1 = new Stack<>();
    	s2 = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	if(s1.isEmpty()) {
    		front = x;
    	}
    	s1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	if(this.empty()) {
    		throw new RuntimeException("没有元素。");
    	}
    	if(!s2.isEmpty()) {
    		return s2.pop();
    	}
    	//s2为空，把s1中的元素全部转到s2，然后弹出s2.
    	while(!s1.isEmpty()) {
    		s2.push(s1.pop());
    	}
        return s2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
    	if(this.empty()) {
    		throw new RuntimeException("没有元素。");
    	}
    	if(!s2.isEmpty()) {
    		return s2.peek();
    	}
        return front;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.empty() && s2.empty();
    }

}
