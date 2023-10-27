package com.leetcode.zixue.linkedlist;

/**
 * @author guanshida
 * @date 2023/9/21
 */
public class TestLinkedList {


    public static void main(String[] args) {
        TestLinkedList a = new TestLinkedList();
//        a.reverseKGroup(ListNode.create(new int[]{1,2,3,4,5}),2).printNodeList();
        a.reverseKGroup(ListNode.create(new int[]{1,2,3,4,5}),3).printNodeList();
    }
    /**
     * 反转链表
     * https://leetcode.cn/problems/reverse-linked-list/description/?envType=study-plan-v2&envId=top-100-liked
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp;
        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;

    }

    /**
     * https://leetcode.cn/problems/reverse-nodes-in-k-group/description/?envType=study-plan-v2&envId=top-100-liked
     * 解法：两层循环，k 个 k 个翻转。如果到末尾了，但是还没有到 k 个，则再把最后一组再翻转一遍即可。
     *          Space:O(1)              Time:O(n)
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode nHead = new ListNode(0);
        nHead.next = head;
        ListNode curHead = head;
        ListNode preTail = nHead;
        ListNode cur = head.next;
        ListNode pre = head;
        ListNode tmp;
        while (cur != null) {
            // 翻转 k 个
            int i = 0;
            for (; i < k-1 && cur != null; i++) {
                tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            }
            if (i >= k - 1) {
                preTail.next = pre;
                curHead.next = cur;
                preTail = curHead;
                curHead = cur;
                pre = cur;
                if (cur != null) {
                    cur = cur.next;
                }
            } else {
                // 到末尾了，再反转一次。
                cur = pre;
                pre = null;
                for (; i >= 0; i--) {
                    tmp = cur.next;
                    cur.next = pre;
                    pre = cur;
                    cur = tmp;
                }
                break;
            }
        }
        return nHead.next;
    }
}
