package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/description/
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
	说明：不允许修改给定的链表。
	
	进阶：
	你是否可以不用额外空间解决此题？
 * @author windSnow
 * @date 创建时间：2018年10月16日
 *
 */
public class LinkedListCycleII {
	public static void main(String[] args) {
		LinkedListCycleII a = new LinkedListCycleII();
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next= n3;
		
//		n1.printNodeList();
		ListNode f = a.detectCycle2(n1);
		System.out.println(f.val);
	}

	/**
	 * Solution2:
	 * 		使用两个指针
	 * @param head
	 * @return
	 */
    public ListNode detectCycle2(ListNode head) {
    	ListNode slow = head;
    	ListNode fast = head;
    	while(fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    		
    		if(slow == fast) {//有环
    			slow = head;
    			while(slow != fast) {
    				slow = slow.next;
    				fast = fast.next;
    			}
    			return fast;
    		}
    	}
    	//无环
    	return null;
    }
    
    /**
     * Solution1:
     * 		使用hashmap
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
    	Map<ListNode,Object> map = new HashMap<>();
    	Object obj = new Object();
    	while(head != null) {
    		boolean flag = map.containsKey(head);
    		if(flag == true) {
    			return head;
    		}
    		map.put(head, obj);
    		head = head.next;
    	}
    	return null;
    }

}
