package com.leetcode.linkedlist;



//Definition for singly-linked list.
/**
	反转一个单链表。
	示例:
	输入: 1->2->3->4->5->NULL
	输出: 5->4->3->2->1->NULL
 *
 */


public class ReverseLinkedList {
	
	public static void main(String[] args) {
		ReverseLinkedList a = new ReverseLinkedList();
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		n1.printNodeList();
		ListNode head = a.reverseList3(n1);
		head.printNodeList();
	}
	/**
	 * Solution3:
	 * 		递归解决
	 * @param head
	 * @return
	 */
	public ListNode reverseList3(ListNode head) {
		if (head == null || head.next == null) return head;
	    ListNode p = reverseList3(head.next);
	    head.next.next = head;
	    head.next = null;
	    return p;
    }
	/**
	 * Solution2:
	 * 		递归解决
	 * @param head
	 * @return
	 */
	public ListNode reverseList2(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode n = head.next;
		head.next = null;
		return dg(head,n);
    }
	public ListNode dg(ListNode before,ListNode after) {
		if(after == null) {
			return before;
		}
		ListNode n = after.next;
		after.next = before;
		
		return dg(after, n);
	}
	/**
	 * Solution1:
	 * 		循环遍历。
	 * @param head
	 * @return
	 */
	public ListNode reverseList1(ListNode head) {
		ListNode before = null;
		ListNode cur = head;
		while(cur != null) {
			ListNode nextNode = cur.next;
			cur.next = before;
			
			before = cur;
			cur = nextNode;
		}
		return before;
	}
	
	
}
