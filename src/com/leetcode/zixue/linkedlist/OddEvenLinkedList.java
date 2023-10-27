package com.leetcode.zixue.linkedlist;

/**
 *
 * 328. 奇偶链表
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 *
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 *
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 *
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,3,5,2,4]
 * 示例 2:
 *
 *
 *
 * 输入: head = [2,1,3,5,6,4,7]
 * 输出: [2,3,6,7,1,5,4]
 *
 *
 * 提示:
 *
 * n ==  链表中的节点数
 * 0 <= n <= 104
 * -106 <= Node.val <= 106
 * 通过次数193,823提交次数297,171
 * @author guanshida
 * @date 2023/2/1
 */
public class OddEvenLinkedList {

    public static void main(String[] args) {
        OddEvenLinkedList a = new OddEvenLinkedList();

        ListNode listNode = ListNode.create(new int[]{1, 2, 3, 4, 5});
        listNode.printNodeList();
        ListNode listNode1 = a.oddEvenList(listNode);
        listNode1.printNodeList();
    }
    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // 先搞两个头结点
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);

        ListNode cur1 = head1, cur2 = head2;
        ListNode cur = head;
        int i = 1;
        while (cur != null) {
            if ((i & 1) == 0) {
                // 偶数
                cur2.next = cur;
                cur2 = cur;
            } else {
                // 奇数
                cur1.next = cur;
                cur1 = cur;
            }
            i++;
            cur = cur.next;
        }
        cur1.next = head2.next;
        cur2.next = null;
        return head1.next;
    }

}
