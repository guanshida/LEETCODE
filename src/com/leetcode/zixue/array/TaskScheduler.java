package com.leetcode.zixue.array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * 621. 任务调度器
 * 给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 *
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的 最短时间 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * 示例 2：
 *
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * 示例 3：
 *
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 *      A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 *
 *
 * 提示：
 *
 * 1 <= task.length <= 104
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 * 通过次数128,508提交次数215,988
 * 请问您在哪类招聘中遇到此题？
 * @author guanshida
 * @date 2023/2/7
 */
public class TaskScheduler {


    // temp
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

    public static void main(String[] args) {
        TaskScheduler a = new TaskScheduler();
//        System.out.println(a.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
        System.out.println(a.leastInterval(new char[]{'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'}, 2));
    }
    /**
     * 方式一：遍历 n 遍，每次往执行一个任务，然后依次找到下一个能执行的任务，放里面。如果找不到，并且还有没有执行的任务就放 null。最后返回长度即可。不能挨着顺序找，应该按着任务数量从大到小找。
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {

        if (tasks == null) {
            return 0;
        }
        if (tasks.length <= 1) {
            return tasks.length;
        }
        PriorityQueue<Character> queue = new PriorityQueue<>();


        int size = tasks.length;
        boolean[] visit = new boolean[tasks.length];
        List<Character> executeList = new ArrayList<>(tasks.length);
        while (size > 0) {
            // 找到第一个元素
            int i = 0;
            while (i < tasks.length) {
                i = this.nextVisit(visit, i);
                if (i >= tasks.length) {
                    // 没有找到
                    break;
                }
                boolean isCheck = this.check(executeList, tasks[i], n);
                if (isCheck) {
                    executeList.add(tasks[i]);
                    visit[i] = true;
                    size --;
                    break;
                } else {
                    i++;
                }
            }
            if (i >= tasks.length) {
                // 没有找到。赋值 null
                executeList.add(null);
            }
        }
        return executeList.size();
    }

    private boolean check(List<Character> executeList, char task, int n) {
        for (int i = executeList.size() - 1; i >= 0; i--) {
            if (executeList.get(i) != null && executeList.get(i) == task) {
                return executeList.size() - 1 - i >= n;
            }
        }
        return true;
    }

    private int nextVisit(boolean[] visit, int i) {
        while (i < visit.length && visit[i]) {
            i++;
        }
        return i;
    }



}
