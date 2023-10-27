package com.leetcode.zixue.linkedlist;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 通过次数545,113提交次数1,031,683
 *
 *
 * @author guanshida
 * @date 2023/1/31
 */
public class PalindromeLinkedList {

    /**
     * 先把链表后半部分反转，然后比较两个链表。最后再把链表复原。
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {


        if (head == null || head.next == null) {
            return true;
        }

        // 获取第二部分
        // 反转后半部分链表
//        ListNode head2 = reverse(head, n / 2);
//        // 比较看链表是否一致
//        boolean flag = compare(head, head2);
//        // 恢复链表
//        head2 = reverse(head2, 0);
//        while ()

        return false;













    }
}
