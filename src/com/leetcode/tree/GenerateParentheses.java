package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/generate-parentheses/description/
     给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

     例如，给出 n = 3，生成结果为：

     [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
     ]
 * @author 管世达
 **/
public class GenerateParentheses {
    public static void main(String[] args) {
        GenerateParentheses a = new GenerateParentheses();
        List<String> s = a.generateParenthesis3(3);
        System.out.println(s);
    }
    /**
     * 解法一：暴力法，生成2^n个解法。然后逐一判断是否符合有效括号的条件。
     *      Time：O(2^2n * n)         Space：O(2^2n * n)
     * 解法二：递归（DFS）。n个有效括号，即有n个左括号和n个右括号。在递归时添加这个限制条件，不符合的不需要遍历其子节点。最后再判断是否是有效括号。
     *
     * 解法三：在解法二的基础上再添加一个条件。value，当遇到左括号+1，遇到右括号-1。要想是有效括号。则value值永不为负。相当于直接递归有效的集合。遍历到叶子节点，直接就是有效括号。
     *
     * @param n 几对括号
     * @return 返回结果
     */
    public List<String> generateParenthesis3(int n) {

        List<String> result = new ArrayList<>();
        if (n == 1) {
            result.add("()");
        } else if (n != 0){
            solution(n, n, "", result);
        }
        return result;
    }

    /**
     *
     * @param leftNum 剩余左括号的数量（还有多少没有用）
     * @param rightNum 剩余右括号的数量
     * @param prev 当前字符串
     * @param result 最后结果。只有leftNum和rightNum都为0时，才设置此值。
     */
    public void solution(int leftNum, int rightNum, String prev, List<String> result) {
        if (leftNum == 0 && rightNum == 0) {
            result.add(prev);
            return;
        }
        if (leftNum > 0) {
            solution(leftNum - 1, rightNum, prev + "(", result);
        }
        if (rightNum > 0 && rightNum > leftNum) {
            solution(leftNum, rightNum - 1, prev + ")", result);
        }
    }
}
