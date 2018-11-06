package com.leetcode.linkedlist;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
	示例:
	给定 1->2->3->4, 你应该返回 2->1->4->3.
	说明:
	你的算法只能使用常数的额外空间。
	你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @author windSnow
 * @date 创建时间：2018年10月16日
 *
 */
public class SwapNodesInPairs {

	
	public static void main(String[] args) {
		SwapNodesInPairs a = new SwapNodesInPairs();
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
//		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
//		n4.next = n5;
		
		n1.printNodeList();
		ListNode head = a.swapPairs2(n1);
		head.printNodeList();
	}
	/**
	 * Solution2:
	 * 		递归
	 * @param head
	 * @return
	 */
	public ListNode swapPairs2(ListNode head) {
		
		if(head == null || head.next == null) {
			return head;
		}
		//1 -> 2 -> 3 -> 4 -> 5
		ListNode nextNode = head.next;
		ListNode temp = nextNode.next;
		head.next = swapPairs2(temp);
		nextNode.next = head;
		
		return nextNode;
	}
	/**
	 * Solution1:
	 * 		循环
	 * @param head
	 * @return
	 */
	public ListNode swapPairs1(ListNode head) {
		
		//先构造一个头结点。
		ListNode h = new ListNode(0);
		h.next = head;
		ListNode pre = h;
		ListNode curr = null;
		
		while(pre.next != null && pre.next.next != null) {
			curr = pre.next;
			pre.next = curr.next;
			curr.next = curr.next.next;
			pre.next.next = curr;
			
			pre = curr;
		}
		//消除头结点。
		return h.next;
	}
}
