package com.leetcode.zixue.string;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *https://leetcode-cn.com/problems/decode-string/
 *给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 *
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeString {

    public static void main(String[] args) {
        DecodeString a = new DecodeString();
        System.out.println(a.decodeString1("3[a]2[bc]"));
        System.out.println(a.decodeString1("3[a2[c]]"));
        System.out.println(a.decodeString1("2[abc]3[cd]ef"));

        System.out.println(a.decodeString1_2("3[a]2[bc]"));
        System.out.println(a.decodeString1_2("3[a2[c]]"));
        System.out.println(a.decodeString1_2("2[abc]3[cd]ef"));

        System.out.println(a.decodeString2("3[a]2[bc]"));
        System.out.println(a.decodeString2("3[a2[c]]"));
        System.out.println(a.decodeString2("2[abc]3[cd]ef"));
    }

    /**
     * 解法一： 栈 来回倒腾。
     *          Time: O(n)      Space:O(n)
     *          优化一下，用栈来回倒腾，比较麻烦，可以使用LinkedList
     *
     * 解法二：递归
     *          思路一： digui = 以字符串为单位。
     *          思路二： 以字符为单位递归。
     *
     *          Time: O(n)      Space:O(n)
     * @param s
     * @return
     */
    public String decodeString1(String s) {

        Stack<String> stack = new Stack<>();
        Stack<String> temp = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                String str = "";
                while (Character.isDigit(s.charAt(i))) {
                    str += s.charAt(i);
                    i++;
                }
                stack.push(str);
            }
            if (s.charAt(i) != ']') {
                stack.push(String.valueOf(s.charAt(i)));
            } else {
                StringBuilder sb = new StringBuilder();
                while (!stack.peek().equals("[")) {
                    temp.push(stack.pop());
                }
                while (!temp.isEmpty()) {
                    sb.append(temp.pop());
                }

                stack.pop();
                sb = this.repeat(sb, Integer.valueOf(stack.pop()));
                stack.push(sb.toString());
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        while (!temp.isEmpty()) {
            sb.append(temp.pop());
        }
        return sb.toString();
    }

    public String decodeString1_2(String s) {

        LinkedList<String> stack = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                String str = "";
                while (Character.isDigit(s.charAt(i))) {
                    str += s.charAt(i);
                    i++;
                }
                stack.add(str);
            }
            if (s.charAt(i) != ']') {
                stack.add(String.valueOf(s.charAt(i)));
            } else {
                LinkedList<String> temp = new LinkedList<>();
                while (!stack.getLast().equals("[")) {
                    temp.addFirst(stack.removeLast());
                }
                stack.removeLast();
                StringBuilder sb = this.repeat(temp, Integer.valueOf(stack.removeLast()));
                stack.add(sb.toString());
            }
        }
        StringBuilder repeat = this.repeat(stack, 1);
        return repeat.toString();
    }

    private StringBuilder repeat(List<String> list, Integer n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < list.size(); j++) {
                sb.append(list.get(j));
            }
        }
        return sb;
    }
    private StringBuilder repeat(StringBuilder list, Integer n) {
        String str = list.toString();
        for (int i = 0; i < n - 1; i++) {
            list.append(str);
        }
        return list;
    }

    public String decodeString2(String s) {
        this.i = 0;
        StringBuilder sb = digui(s);
        return sb.toString();

    }

    private int i = 0;
    private StringBuilder digui(String s) {
        StringBuilder sb = new StringBuilder();
        if (i >= s.length()) {
            return sb;
        }

        int n = 0;
        while (i < s.length()) {
            if (Character.isDigit(s.charAt(i))) {
                n = n * 10 + (s.charAt(i) - 48);
            } else if (s.charAt(i) == '[') {
                i ++;
                StringBuilder digui = this.digui(s);
                sb.append(this.repeat(digui, n));
                n = 0;
            } else if (s.charAt(i) == ']') {
                return sb;
            } else {
                sb.append(s.charAt(i));
            }
            i++;
        }

        return sb;
    }


}
