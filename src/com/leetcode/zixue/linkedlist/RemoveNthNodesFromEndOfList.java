package com.leetcode.zixue.linkedlist;

import com.leetcode.linkedlist.ReverseLinkedList;


/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
	给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
	
	示例：
	给定一个链表: 1->2->3->4->5, 和 n = 2.
	当删除了倒数第二个节点后，链表变为 1->2->3->5.
	
	说明：
	给定的 n 保证是有效的。
	
	进阶：
	你能尝试使用一趟扫描实现吗？
 * @author windSnow
 * @date 创建时间：2018年10月23日
 *
 */
public class RemoveNthNodesFromEndOfList {
	public static void main(String[] args) {
		RemoveNthNodesFromEndOfList a = new RemoveNthNodesFromEndOfList();
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
		ListNode head = a.removeNthFromEnd(n1,5);
		head.printNodeList();
	}
	/**
	 * 解法1：先求长度。然后再遍历一遍去掉第(length-n)个节点(从0开始)，遍历两遍。
	 * 		Time:O(n)
	 * 		Space:O(1)
	 * 解法2：添加一个头指针h:h.next=head。两个指针：fast/slow;初始值为slow=h，slow=第n-1个节点。然后fast/slow一起向后移动。
	 * 	当fast.next为null时,删除slow的下一个节点即可。
	 * 		优点，相较于解法1，少遍历了一次。
	 * 		Time:O(n)
	 * 		Space:O(1)
	 * @param head
	 * @param n
	 * @return
	 */
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if(head == null || head.next == null) {
    		return null;
    	}
    	
    	ListNode h = new ListNode(0);
    	h.next = head;
    	ListNode slow = h;
    	//计算fast
    	ListNode fast = h;
    	for (int i = 0; i < n; i++) {
			fast = fast.next;
		}
    	
    	while(fast.next != null) {
    		slow = slow.next;
    		fast = fast.next;
    	}
    	slow.next = slow.next.next;
    	
        return h.next;
    }
}
