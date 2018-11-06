package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个链表，判断链表中是否有环。

	进阶：
	你能否不使用额外空间解决此题？
 * @author windSnow
 * @date 创建时间：2018年10月16日
 *
 */
public class LinkedListCycle {
	public static void main(String[] args) {
		LinkedListCycle a = new LinkedListCycle();
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
//		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
//		n4.next = n5;
//		n5.next=n1;
		
//		n1.printNodeList();
		boolean f = a.hasCycle2(n1);
		System.out.println(f);
	}

	/**
	 * Solution2:
	 * 		使用两个指针
	 * @param head
	 * @return
	 */
    public boolean hasCycle2(ListNode head) {
    	ListNode slow = head;
    	ListNode fast = head.next;
    	while(fast != null && fast.next != null) {
    		if(fast == slow) {
    			return true;
    		}
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	return false;
    }
    
    /**
     * Solution1:
     * 		使用hashmap
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {
    	Map<ListNode,Object> map = new HashMap<>();
    	Object obj = new Object();
    	while(head != null) {
    		boolean flag = map.containsKey(head);
    		if(flag == true) {
    			return true;
    		}
    		map.put(head, obj);
    		head = head.next;
    	}
    	return false;
    }

}
