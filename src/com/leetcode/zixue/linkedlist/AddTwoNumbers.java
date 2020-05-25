package com.leetcode.zixue.linkedlist;

/**
 * 地址：https://leetcode-cn.com/problems/add-two-numbers/
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumbers {
    /**
     * Time:O(max(n+m))  Space:O(max(m,n))
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode curL1 = l1;
        ListNode curL2 = l2;
        int preBit = 0;

        ListNode head = new ListNode(0);
        ListNode curNode = head;
        while (curL1 != null && curL2 != null) {
            preBit = addNode(curNode, curL1.val + curL2.val + preBit);
            curNode = curNode.next;
            curL1 = curL1.next;
            curL2 = curL2.next;
        }
        while (curL1 != null) {
            preBit = addNode(curNode, curL1.val + preBit);
            curNode = curNode.next;
            curL1 = curL1.next;
        }
        while (curL2 != null) {
            preBit = addNode(curNode, curL2.val + preBit);
            curNode = curNode.next;
            curL2 = curL2.next;
        }

        if (preBit != 0) {
            ListNode listNode = new ListNode(preBit);
            curNode.next = listNode;
        }
        return head.next;
    }

    private int addNode(ListNode curNode, int val) {
        int carry = 0;
        if (val >= 10) {
            carry = 1;
            val -= 10;
        }
        ListNode listNode = new ListNode(val);
        curNode.next = listNode;
        return carry;
    }

    /**
     * 递归
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ttt(head, l1, l2, 0);
        return head.next;
    }

    private void ttt(ListNode head, ListNode l1, ListNode l2, int i) {
        if (l1 == null && l2 == null && i == 0) {
            return;
        }
        int val = i;
        int bit = 0;
        if (l1 != null) {
            val += l1.val;
        }
        if (l2 != null) {
            val += l2.val;
        }
        if (val >= 10) {
            bit = 1;
            val -= 10;
        }
        head.next = new ListNode(val);
        ttt(head.next, l1 == null ? null : l1.next, l2 == null ? null : l2.next, bit);
    }

    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {

        ListNode curL1 = l1;
        ListNode curL2 = l2;
        int preBit = 0;

        ListNode head = new ListNode(0);
        ListNode curNode = head;
        while (curL1 != null || curL2 != null) {

            int val = preBit;
            preBit = 0;
            val += curL1 == null ? 0 : curL1.val;
            val += curL2 == null ? 0 : curL2.val;

            if (val >= 10) {
                preBit = 1;
                val -= 10;
            }
            ListNode listNode = new ListNode(val);
            curNode.next = listNode;
            curNode = curNode.next;
            curL1 = curL1 == null ? null : curL1.next;
            curL2 = curL2 == null ? null : curL2.next;
        }

        if (preBit != 0) {
            curNode.next = new ListNode(preBit);
        }
        return head.next;
    }

}