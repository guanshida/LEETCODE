package com.leetcode.zixue.recursive_divide;/**
 * Created by windSnow on 2018/11/12.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/k-th-symbol-in-grammar/description/
     在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
     给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始）

     例子:
     输入: N = 1, K = 1
     输出: 0

     输入: N = 2, K = 1
     输出: 0

     输入: N = 2, K = 2
     输出: 1

     输入: N = 4, K = 5
     输出: 1

     解释:
     第一行: 0
     第二行: 01
     第三行: 0110
     第四行: 01101001

     注意：
     N 的范围 [1, 30].
     K 的范围 [1, 2^(N-1)].

 * @author 管世达
 * @create 2018-11-12 20:09
 **/
public class KthSymbolInGrammer {

    public static void main(String[] args) {
        KthSymbolInGrammer a = new KthSymbolInGrammer();
        System.out.println(a.kthGrammar3(4,5));
    }
    /**
     * 解法一：循环
     *      Time: O(2^n)
     *      Space: O(2^n)
     * 解法二：递归
     *      Time: O(2^n)
     *      Space: O(2^n)
     * 解法三：递归，类似于折半查找，也类似于二叉搜索树查找某个值。
     *      Time: O(n)
     *      Space: O(n)
     * @param n
     * @param k
     * @return
     */
    public int kthGrammar1(int n, int k) {

        if(n == 0) {
            return 0;
        }

        List<Character> chars = new LinkedList<>();
        List<Character> temp = new LinkedList<>();
        chars.add('0');
        for (int i = 0; i < n; i++) {
            for (Character c : chars) {
                if(c == '0') {
                    temp.add('0');
                    temp.add('1');
                } else {
                    temp.add('1');
                    temp.add('0');
                }
            }
            List<Character> a = chars;
            chars = temp;
            temp = a;
            temp.clear();
        }
        return chars.get( k - 1) - '0';
    }
    public int kthGrammar2(int n, int k) {
        List<Character> bytes = this.recursive2(n);
        if(bytes.size() >= k) {
            return bytes.get(k - 1) - '0';
        }

        return 0;
    }
    public List<Character> recursive2(int n) {
        List<Character> list = new ArrayList<>();
        if(n == 1) {
            list.add('0');
            return list;
        }
        List<Character> l = recursive2(n - 1);
        for (Character c : l) {
            if(c == '0') {
                list.add('0');
                list.add('1');
            } else {
                list.add('1');
                list.add('0');
            }
        }
        return list;
    }
    public int kthGrammar3(int n, int k) {
        return recursive3(0,n,k);
    }
    public int recursive3(int root, int n, int k) {
        if(n <= 1) {
            return root;
        }
        int[] arr = new int[2];
        if(root == 0) {
            arr[0] = 0;
            arr[1] = 1;
        } else {
            arr[0] = 1;
            arr[1] = 0;
        }
        int i = 1 << (n-2);
        if(k <= i) {
            return recursive3(arr[0], n - 1, k);
        } else {
            return recursive3(arr[1], n - 1, k - i);
        }
    }


}
