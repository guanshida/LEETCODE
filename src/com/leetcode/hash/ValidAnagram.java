package com.leetcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/valid-anagram/description/
	给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
	
	示例 1:
	输入: s = "anagram", t = "nagaram"
	输出: true
	
	示例 2:
	输入: s = "rat", t = "car"
	输出: false
	说明:
	你可以假设字符串只包含小写字母。
	
	进阶:
	如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
	
	
	即：每个字母的个数都一样。
 * @author windSnow
 * @date 创建时间：2018年10月27日
 *
 */
public class ValidAnagram {
	
	public static void main(String[] args) {
		String s = "ana你gram";
		String t = "nagara你m";
		ValidAnagram a = new ValidAnagram();
		System.out.println(a.isAnagram(s, t));
	}
	/**
	 * Time: O(n)
	 * Space: O(n)
	 * @param s
	 * @param t
	 * @return
	 */
	public boolean isAnagram(String s, String t) {

		if(s == null || t == null) {
			if(s != null || t != null) {
				return false;
			}
			return true;
		}
		char[] sc = s.toCharArray();
		char[] tc = t.toCharArray();
		if(sc.length != tc.length) {
			return false;
		}
		
		Map<Character, Integer> map = new HashMap<Character, Integer>(26);

		for (int i = 0; i < sc.length; i++) {
			if(map.containsKey(sc[i])) {
				map.put(sc[i], map.get(sc[i]) + 1);
			} else {
				map.put(sc[i], 1);
			}
			
			if(map.containsKey(tc[i])) {
				map.put(tc[i], map.get(tc[i]) - 1);
			} else {
				map.put(tc[i], -1);
			}
			
		}
		for (Integer i : map.values()) {
			if(i != 0) {
				return false;
			}
		}
		return true;
	}
}
