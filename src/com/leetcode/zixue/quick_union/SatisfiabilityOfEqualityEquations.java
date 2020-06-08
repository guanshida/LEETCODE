package com.leetcode.zixue.quick_union;

/**
 * https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
 *
 * 990. 等式方程的可满足性
 *
 * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
 *
 * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
 *
 *  
 *
 * 示例 1：
 *
 * 输入：["a==b","b!=a"]
 * 输出：false
 * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
 * 示例 2：
 *
 * 输出：["b==a","a==b"]
 * 输入：true
 * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
 * 示例 3：
 *
 * 输入：["a==b","b==c","a==c"]
 * 输出：true
 * 示例 4：
 *
 * 输入：["a==b","b!=c","c==a"]
 * 输出：false
 * 示例 5：
 *
 * 输入：["c==c","b==d","x!=z"]
 * 输出：true
 *  
 *
 * 提示：
 *
 * 1 <= equations.length <= 500
 * equations[i].length == 4
 * equations[i][0] 和 equations[i][3] 是小写字母
 * equations[i][1] 要么是 '='，要么是 '!'
 * equations[i][2] 是 '='
 * 通过次数5,769提交次数13,077
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SatisfiabilityOfEqualityEquations {
    public static void main(String[] args) {
        SatisfiabilityOfEqualityEquations a = new SatisfiabilityOfEqualityEquations();
        System.out.println(a.equationsPossible(new String[]{"a!=a"}));
        System.out.println(a.equationsPossible(new String[]{"a==b", "b!=a"}));
        System.out.println(a.equationsPossible(new String[]{"b==a","a==b"}));
        System.out.println(a.equationsPossible(new String[]{"a==b","b==c","a==c"}));
        System.out.println(a.equationsPossible(new String[]{"a==b","b!=c","c==a"}));
        System.out.println(a.equationsPossible(new String[]{"c==c","b==d","x!=z"}));
    }

    /**
     * 解法一：并查集。== 的直接用union并起来。然后挨个判断 != 的是否在一个集合里面。
     *          Time: O(n)      Space: O(1) 因为最多26个小写字母。
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        if (equations.length == 0) {
            return true;
        }
        AndCheck ac = new AndCheck(26);
        for (String str : equations) {
            if (str.charAt(1) == '=' && str.charAt(0) != str.charAt(3)) {
                ac.union(str.charAt(0) - 97, str.charAt(3) - 97);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                boolean connected = ac.connected(str.charAt(0) - 97, str.charAt(3) - 97);
                if (connected) {
                    return false;
                }
            }
        }
        return true;
    }
}
