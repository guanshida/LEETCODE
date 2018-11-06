package com.leetcode.linkedlist;

/**
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
	k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
	
	示例 :
	给定这个链表：1->2->3->4->5
	
	当 k = 2 时，应当返回: 2->1->4->3->5
	当 k = 3 时，应当返回: 3->2->1->4->5
	
	说明 :
	你的算法只能使用常数的额外空间。
	你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @author windSnow
 * @date 创建时间：2018年10月16日
 *
 */
public class ReverseNodesInKGroup {

	
	public static void main(String[] args) {
		ReverseNodesInKGroup a = new ReverseNodesInKGroup();
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
		ListNode head = a.reverseKGroup2(n1,2);
		head.printNodeList();
	}
	/**
	 * Solution2:
	 * 		遍历一遍，反转k个...反转k个,如果到末尾但没到k，则纠正一下。把最后m个再反转一次即可。
	 * 		时间复杂度:O(n)
	 * 		空间复杂度:O(1)
	 * @param head
	 * @param k
	 * @return
	 * 
	 */
	public ListNode reverseKGroup2(ListNode head, int k) {
	
		ListNode h = new ListNode(0);
		h.next = head;
		ListNode start = h;
		while(start != null && start.next != null && start.next.next != null) {
			ListNode prev = start.next;
			ListNode curr = prev.next;
			int i = 0;
			//从start开始反转k个，不算start
			while(curr != null && i< k - 1) {
				ListNode temp = curr.next;
				curr.next = prev;
				
				prev = curr;
				curr = temp;
				i ++;
			}
			if(i >= k - 1) {//循环够了k个。继续循环
				ListNode temp = start.next;
				temp.next = curr;
				start.next = prev;
				start = temp;
			} else {//循环不够k个，但是到列表末尾了，则反转最后i个(由于把最后一个节点的next要置成null，放在循环里面了，所以实际上循环i+1次)。然后返回。
				curr = prev;
				prev = null;
				for (int j = 0; j <= i; j++) {
					ListNode temp = curr.next;
					curr.next = prev;
					prev = curr;
					curr = temp;
				}
				break;
			}
		}
		return h.next;
	}
	/**
	 * Solution1:
	 * 		先求head的长度。然后反转len/k次。
	 * 		时间复杂度：O(n)
	 * 		空间复杂度：O(1)
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup1(ListNode head, int k) {
		int len = 0;
		ListNode h = head;
		while(h != null) {
			len++;
			h = h.next;
		}
		h = new ListNode(0);
		h.next = head;
		ListNode start = h;
		
		for (int i = 0; i < len/k; i++) {
			start = reverse(start, k);
		}
		return h.next;
	}

	private ListNode reverse(ListNode start, int k) {
		ListNode curr = start.next.next;
		ListNode prev = start.next;
		
		for (int i = 0; i < k - 1; i++) {
			ListNode temp = curr.next;
			curr.next = prev;
			
			prev = curr;
			curr = temp;
		}
		ListNode end = start.next;
		end.next = curr;
		start.next = prev;
		return end;
	}

}
