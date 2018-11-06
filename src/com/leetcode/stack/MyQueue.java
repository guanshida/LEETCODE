package com.leetcode.stack;

import java.util.Stack;

class MyQueue {

	public static void main(String[] args) {
		MyQueue queue = new MyQueue();

		queue.push(1);
//		queue.push(2);  
		System.out.println(queue.peek());  // 返回 1
		System.out.println(queue.pop());   // 返回 1
		System.out.println(queue.empty()); // 返回 false	
	}
	private Stack<Integer> stack = new Stack<>();
    /** Initialize your data structure here. */
    public MyQueue() {
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	if(stack.isEmpty()) {
    		stack.push(x);
    		return ;
    	}
        Stack<Integer> s = new Stack<>();
        while (!stack.isEmpty()) {
			s.push(stack.pop());
		}
        stack.push(x);
        while(!s.isEmpty()) {
        	stack.push(s.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
