package com.leetcode.zixue.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/group-anagrams/?envType=study-plan-v2&envId=top-100-liked
 * 49. 字母异位词分组
 * 中等
 * 1.6K
 * 相关企业
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 *
 *
 *
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 示例 2:
 *
 * 输入: strs = [""]
 * 输出: [[""]]
 * 示例 3:
 *
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 * 通过次数
 * 510.5K
 * 提交次数
 * 752.7K
 * 通过率
 * 67.8%
 * 请问您在哪类招聘中遇到此题？
 * 1/5
 * 社招
 * 校招
 * 实习
 * 未遇到
 *
 * @author guanshida
 * @date 2023/9/4
 */
public class GroupAnagrams {


    public static void main(String[] args) {
        GroupAnagrams a = new GroupAnagrams();
        System.out.println(a.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(a.groupAnagrams(new String[]{""}));
        System.out.println(a.groupAnagrams(new String[]{"a"}));

    }

    /**
     * 就是比较两个单词是不是异位词，但因为涉及到重复比较，所以可以存储一个中间形态，叫做异位词标识。只需要跟这个标识进行比较即可。也就是 Map<标识,List<词>>
     *
     * 解法一：把异位词标识为：排序之后的字符串。因为异位词排序后字符串肯定一样。
     * 解法二：把异位词标识为：字母的个数。因为异位词每个字母的个数肯定一样。
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strs) {
            String sign = newSign(word);
            map.putIfAbsent(sign, new ArrayList<>());
            map.get(sign).add(word);
        }
        return new ArrayList<>(map.values());
    }

    private String newSign(String word) {
        int[] signArr = new int[26];
        for (int i = 0; i < word.length(); i++) {
            signArr[word.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < signArr.length; i++) {
            if (signArr[i] > 0) {
                sb.append((char) (i + 'a')).append(signArr[i]);
            }
        }
        return sb.toString();
    }




}
