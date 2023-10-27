package com.leetcode.zixue.linkedlist;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/merge-k-sorted-lists/?envType=study-plan-v2&envId=top-100-liked
 * 23. 合并 K 个升序链表
 * 困难
 * 相关标签
 * 相关企业
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * 通过次数
 * 708.9K
 * 提交次数
 * 1.2M
 * 通过率
 * 58.4%
 * @author windSnow
 * @date 创建时间：2018年10月23日
 *
 */
public class MergeKSortedLists {

	public static void main(String[] args) {
		MergeKSortedLists a = new MergeKSortedLists();
		a.mergeKLists(new ListNode[]{ListNode.create(new int[]{1, 4, 5}), ListNode.create(new int[]{1, 3, 4}), ListNode.create(new int[]{2, 6})}).printNodeList();
	}

	/**
	 * 解法一：挨个比较。
	 * 		Space:O(1)		Time:O(m*n)							m为列表中最大的长度。n 位 列表的个数。
	 * 解法二：使用小底堆。
	 * 		Space:O(n)		Time:O(m*logn)							m为列表中最大的长度。n 位 列表的个数。
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		if (lists == null || lists.length == 0) {
			return null;
		}

		PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				queue.add(lists[i]);
			}
		}
		ListNode head = new ListNode(0);
		ListNode cur = head;
		while (!queue.isEmpty()) {
			ListNode poll = queue.poll();
			cur.next = poll;
			cur = poll;
			if (poll.next != null) {
				queue.add(poll.next);
			}
		}
		return head.next;
	}

}
