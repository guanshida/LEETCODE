package com.leetcode.zixue;

import com.leetcode.zixue.linkedlist.ListNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author guanshida
 * @date 2023/1/17
 */
public class TestController {
    public static void main(String[] args) {
    }

    public ListNode reverseList(ListNode root) {
        if (root == null) {
            return root;
        }
        ListNode l = null;
        ListNode r = root;
        while (r != null) {
            ListNode tmp = r.next;
            r.next = l;
            l = r;
            r = tmp;
        }
        return l;
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        int len = this.lenth(head);
        if (len < k) {
            return head;
        }
        ListNode pre = null;
        ListNode cur = head;
        int num = len / k;// num >=1
        for (int i = 0; i < num; i++) {
            ListNode curHead = reverse(cur, k);
            if (pre != null) {
                pre.next = curHead;
            } else {
                head = curHead;
            }
            pre = skip(curHead, k - 1);
            cur = pre.next;
        }

        return head;
    }

    private ListNode reverse(ListNode root, int k) {
        if (root == null) {
            return root;
        }
        ListNode l = null;
        ListNode r = root;
        while (r != null && k-- > 0) {
            ListNode tmp = r.next;
            r.next = l;
            l = r;
            r = tmp;
        }
        root.next = r;
        return l;
    }

    private ListNode skip(ListNode curHead, int i) {
        while (i-- > 0) {
            curHead = curHead.next;
        }
        return curHead;
    }

    private int lenth(ListNode head) {
        int i = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            i++;
        }
        return i;
    }

    public int[] twoSum(int[] nums, int target) {

//        Arrays.sort(nums);
//        int left = 0;
//        int right = nums.length - 1;
//        while (left < right) {
//            int tt = nums[left] + nums[right];
//            if (tt > target) {
//                right--;
//            } else if (tt < target) {
//                left++;
//            } else {
//                return new int[]{left, right};
//            }
//        }
//        return null;

        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return null;
    }
}
