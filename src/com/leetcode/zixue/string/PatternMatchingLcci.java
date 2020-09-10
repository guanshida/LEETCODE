package com.leetcode.zixue.string;

/**
 *
 * https://leetcode-cn.com/problems/pattern-matching-lcci/
 * 面试题 16.18. 模式匹配
 *
 * 你有两个字符串，即pattern和value。 pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"（其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。编写一个方法判断value字符串是否匹配pattern字符串。
 *
 * 示例 1：
 *
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 * 示例 2：
 *
 * 输入： pattern = "abba", value = "dogcatcatfish"
 * 输出： false
 * 示例 3：
 *
 * 输入： pattern = "aaaa", value = "dogcatcatdog"
 * 输出： false
 * 示例 4：
 *
 * 输入： pattern = "abba", value = "dogdogdogdog"
 * 输出： true
 * 解释： "a"="dogdog",b=""，反之也符合规则
 * 提示：
 *
 * 0 <= len(pattern) <= 1000
 * 0 <= len(value) <= 1000
 * 你可以假设pattern只包含字母"a"和"b"，value仅包含小写字母。
 * 通过次数1,803提交次数5,788
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pattern-matching-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PatternMatchingLcci {

    public static void main(String[] args) {
        PatternMatchingLcci p = new PatternMatchingLcci();

        System.out.println(p.patternMatching("bbbbbbbbabbbbbbbbbbbabbbbbbba", "zezezezezezezezezkxzezezezezezezezezezezezkxzezezezezezezezkx"));
        System.out.println(p.patternMatching("baabab", "eimmieimmieeimmiee"));
        System.out.println(p.patternMatching("aaaaaaaaaabaaaaa", "sngnvssngnvssngnvssngnvssngnvssngnvssngnvssngnvssngnvssngnvsngvsnsngnvssngnvssngnvssngnvssngnvs"));
        System.out.println(p.patternMatching("", ""));
        System.out.println(p.patternMatching("q", "a"));
        System.out.println(p.patternMatching("abba", "dogcatcatdog"));
        System.out.println(p.patternMatching("abba", "dogcatcatfish"));
        System.out.println(p.patternMatching("aaaa", "dogcatcatdog"));
        System.out.println(p.patternMatching("abba", "dogdogdogdog"));

    }

    public boolean patternMatching(String pattern, String str) {

        if ("".equals(str)) {
            return isOne(pattern);
        }

        String a, b;
        for (int i = 0; i <= str.length(); i++) {
            a = str.substring(0, i);
            for (int j = i; j <= str.length(); j++) {
                b = str.substring(i, j);
                if (!a.equals(b)) {
                    if (testPattern(str, pattern, a, b) || testPattern(str, pattern, b, a)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isOne(String pattern) {
        if (pattern.length() <= 1) {
            return true;
        }
        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(0) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private boolean testPattern(String str, String pattern, String a, String b) {
        int i = 0;
        int j = 0;
        while (j < pattern.length()) {
            if (pattern.charAt(j++) == 'a') {
                if (!this.equalsSubString(str, i, a)) {
                    return false;
                }
                i = i + a.length();
            } else {
                if (!this.equalsSubString(str, i, b)) {
                    return false;
                }
                i = i + b.length();
            }
        }

        return i >= str.length();
    }

    private boolean equalsSubString(String str, int i, String b) {
        if ("".equals(b)) {
            return true;
        }
        int j = 0;
        while (j < b.length()) {
            if (j + i >= str.length() || str.charAt(i + j) != b.charAt(j)) {
                return false;
            }
            j++;
        }
        return true;
    }
}
