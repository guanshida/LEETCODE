package com.leetcode.zixue.array;


import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/daily-temperatures/
 *  739. 每日温度
 *根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DailyTemperatures {

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        System.out.println(Arrays.toString(dt.dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));

    }

    /**
     * 解法一： 暴力求解。两遍for循环。
     *          Time: O(n^2)          Space:O(1)
     * 解法二： 单调栈。查找i右面第一个比它自己大的元素，可以使用单调栈，从右往左遍历，保持栈从下至上单调递减。
     *          Time: O(n)              Space:O(n)
     * @param t
     * @return
     */
    public int[] dailyTemperatures2(int[] t) {
        if (t.length <= 1) {
            return new int[]{0};
        }
        int[] arr = new int[t.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = t.length - 1; i >= 0; i--) {

            while (!stack.isEmpty()&&t[stack.peek()] <= t[i]) {
                stack.pop();
            }
            arr[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
            stack.push(i);
        }
        return arr;
    }
}
