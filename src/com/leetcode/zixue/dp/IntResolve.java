package com.leetcode.zixue.dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 一个整数可以被分解为多个整数的乘积，例如，6 可以分解为2x3。请使用递归编程的方法，为给定的整数 n，找到所有可能的分解（1 在
 解中最多只能出现 1 次）。例如，输入 8，输入 8，输出是可以是 1x8, 8x1, 2x4, 4x2,...
 * @author 管世达
 * @create 2018-12-25
 **/
public class IntResolve {
    public static void main(String[] args) {
        IntResolve a = new IntResolve();
        a.solution(30);
//        System.out.println(solution);
    }

    private Set<String> exist = new HashSet<>();
    public void solution(int n) {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(n);
        dg(list);
    }

    /**
     *
     * 时间复杂度：O(n^2)   空间复杂度：O(n^2)
     * @param list
     */
    private void dg(ArrayList<Integer> list) {
        //输出结果
        print(list);
        for (int index = 0; index < list.size(); index ++) {
            //取一个数，继续分接
            Integer num = list.get(index);
            //分解num
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {//分解到了，递归
                    //准备数据
                    list.remove(num);
                    list.add(i);
                    list.add(num / i);

                    dg(list);

                    //恢复现场
                    list.add(num);
                    list.remove((Integer) i);
                    list.remove((Integer) (num / i));
                }
            }
        }
    }

    private void print(ArrayList<Integer> list) {
        String s = list.toString();
        if (exist.contains(s)) {
            return ;
        }
        exist.add(s);
        if (list.size() > 1) {
            System.out.println(list);
        }

        //可以把1插在任何位置
        for (int i = 0; i <= list.size(); i++) {
            list.add(i, 1);
            System.out.println(list);
            list.remove(i);
        }
    }
}
