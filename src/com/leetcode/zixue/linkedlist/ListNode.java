package com.leetcode.zixue.linkedlist;

public class ListNode {
	public int val;
	public ListNode next;

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

	public static ListNode create(int[] arr) {
		if (arr == null) {
			return null;
		}
		ListNode head = new ListNode(arr[0]);
		ListNode cur = head;
		for (int i = 1; i < arr.length; i++) {
			ListNode node = new ListNode(arr[i]);
			cur.next = node;
			cur = node;
		}
		return head;
	}
}
