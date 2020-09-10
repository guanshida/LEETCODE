package com.leetcode.zixue.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * 通过次数150,317提交次数275,582
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber a = new LetterCombinationsOfAPhoneNumber();
        System.out.println(a.letterCombinations1("23"));
    }

    private String[] arr = {"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations1(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return list;
        }
        digui(digits, 0, "", list);
        return list;
    }

    private void digui(String digits, int i, String pre, List<String> list) {
        if (i >= digits.length()) {
            list.add(pre);
            return;
        }

        String s = arr[digits.charAt(i) - '0' - 1];
        for (int j = 0; j < s.length(); j++) {
            digui(digits, i + 1, pre + s.charAt(j), list);
        }
    }

}
