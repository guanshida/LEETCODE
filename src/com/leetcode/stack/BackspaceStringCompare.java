package com.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/backspace-string-compare/description/
	给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
	
	示例 1：
	输入：S = "ab#c", T = "ad#c"
	输出：true
	解释：S 和 T 都会变成 “ac”。
	
	示例 2：
	输入：S = "ab##", T = "c#d#"
	输出：true
	解释：S 和 T 都会变成 “”。
	
	示例 3：
	输入：S = "a##c", T = "#a#c"
	输出：true
	解释：S 和 T 都会变成 “c”。
	
	示例 4：
	输入：S = "a#c", T = "b"
	输出：false
	解释：S 会变成 “c”，但 T 仍然是 “b”。
	
	提示：
	1 <= S.length <= 200
	1 <= T.length <= 200
	S 和 T 只含有小写字母以及字符 '#'。
 * @author windSnow
 * @date 创建时间：2018年10月18日
 *
 */
public class BackspaceStringCompare {
	
	/**
	 * Solution3:  最快的
	 * 		时间复杂度：O(s+t)
	 * @param S
	 * @param T
	 * @return
	 */
    public boolean backspaceCompare3(String S, String T) {
    	
        return foo(S).equals(foo(T));
    }
    public String foo(String s) {
    	StringBuilder sb = new StringBuilder();
    	
    	for (char c : s.toCharArray()) {
			if(c == '#') {
				if(sb.length() != 0) {
					sb.deleteCharAt(sb.length() - 1);
				}
			} else {
				sb.append(c);
			}
		}
    	return sb.reverse().toString();
    }

	/**
	 * Solution2:
	 * 		时间复杂度：O(s+t)
	 * @param S
	 * @param T
	 * @return
	 */
    public boolean backspaceCompare(String S, String T) {
    	Stack<Character> s1 = new Stack<>();
    	Stack<Character> s2 = new Stack<>();
    	
    	char[] s = S.toCharArray();
    	char[] t = T.toCharArray();
    	
    	for (int i=0; i < s.length; i++) {
			if(s[i] == '#') {
				if(!s1.isEmpty())
					s1.pop();
			} else {
				s1.push(s[i]);
			}
		}
    	for (int i=0; i < t.length; i++) {
			if(t[i] == '#') {
				if(!s2.isEmpty())
					s2.pop();
			} else {
				s2.push(t[i]);
			}
		}
    	if(s1.size() != s2.size()) {
    		return false;
    	}
    	while(!s1.isEmpty()) {
    		if(s1.pop() != s2.pop()) {
    			return false;
    		}
    	}
        return true;
    }
    /**
     * Solution1:
     * 		时间复杂度：O(len(s,t))
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare1(String S, String T) {
    	Stack<Character> s1 = new Stack<>();
    	Stack<Character> s2 = new Stack<>();
    	
    	char[] s = S.toCharArray();
    	char[] t = T.toCharArray();
    	int len = s.length > t.length ? s.length : t.length;
    	
    	for (int i=0; i < len; i++) {
    		if(i < s.length) {
    			if(s[i] == '#') {
    				if(!s1.isEmpty())
    					s1.pop();
    			} else {
    				s1.push(s[i]);
    			}
    		}
    		if(i < t.length) {
    			if(t[i] == '#') {
    				if(!s2.isEmpty())
    					s2.pop();
    			} else {
    				s2.push(t[i]);
    			}
    		}
    	}
    	if(s1.size() != s2.size()) {
    		return false;
    	}
    	while(!s1.isEmpty()) {
    		if(s1.pop() != s2.pop()) {
    			return false;
    		}
    	}
    	
    	return true;
    }

}
