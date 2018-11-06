package com.leetcode.stack;

import java.util.Stack;


/**
 * https://leetcode-cn.com/problems/valid-parentheses/description/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

	有效字符串需满足：
	左括号必须用相同类型的右括号闭合。
	左括号必须以正确的顺序闭合。
	注意空字符串可被认为是有效字符串。
	
	示例 1:
	输入: "()"
	输出: true

	示例 2:
	输入: "()[]{}"
	输出: true
	
	示例 3:
	输入: "(]"
	输出: false
 * @author windSnow
 * @date 创建时间：2018年10月18日
 *
 */
public class ValidParenttheses {
	public static void main(String[] args) {
		ValidParenttheses a = new ValidParenttheses();
		boolean b = a.isValid("()");
		System.out.println(b);
	}

    public boolean isValid(String s) {
    	if(s == null || s.equals("")) {
    		return true;
    	}
    	if(s.length() % 2 != 0) {
    		return false;
    	}
        Stack<Character> stack = new Stack<>();
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
			if(this.isLeftParenttheses(c)) {
				stack.push(c);
			}
			if(this.isRightParenttheses(c)) {
				if(stack.isEmpty()) {
					return false;
				}
				char pop = stack.pop();
				if(!isPair(pop,c)) {
					return false;
				}
			}
		}
    	if(!stack.isEmpty()) {
    		return false;
    	}
    	return true;
    }
    public boolean isPair(char pop, char c) {
		if(pop == '(' && c == ')') {
			return true;
		}
		if(pop == '[' && c == ']') {
			return true;
		}
		if(pop == '{' && c == '}') {
			return true;
		}
		return false;
	}

	public boolean isLeftParenttheses(char c) {
    	if(c == '(' || c == '[' || c == '{') {
    		return true;
    	}
    	return false;
    }
    public boolean isRightParenttheses(char c) {
    	if(c == ')' || c == ']' || c == '}') {
    		return true;
    	}
    	return false;
    }
    
    
    
    
}
