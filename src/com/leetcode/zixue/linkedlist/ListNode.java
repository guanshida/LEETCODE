package com.leetcode.zixue.linkedlist;

public class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
	public void printNodeList() {
		System.out.println("**");
		ListNode l = this.next;
		System.out.print(this.val + " ->");
		while(l!=null) {
			System.out.print(l.val + " ->");
			l = l.next;
		}
		System.out.println();
	}
}
