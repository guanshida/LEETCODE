package com.leetcode.zixue.array;

import java.util.Stack;

/**
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 *
 * 84. 柱状图中最大的矩形
 *
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

 求在该柱状图中，能够勾勒出来的矩形的最大面积。

  



 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。

  



 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。

  

 示例:

 输入: [2,1,5,6,2,3]
 输出: 10

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 管世达
 * @create 2020-05-30
 **/
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        LargestRectangleInHistogram l = new LargestRectangleInHistogram();
        System.out.println(l.largestRectangleArea11(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(l.largestRectangleArea11(new int[]{2}));
        System.out.println(l.largestRectangleArea11(new int[]{2, 1, 2}));


        System.out.println(l.largestRectangleArea12(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(l.largestRectangleArea12(new int[]{2}));
        System.out.println(l.largestRectangleArea12(new int[]{2, 1, 2}));

    }

    /**
     * 解法一：两层循环，第一层f(i) 表示以 heights[i] 为高度的最大面积
     *          Time: O(n^2)        Space:O(1)
     *
     * 解法二：单调栈：固定高度 height[i]，求 i 左边最近的小于 height[i] 的高度。和 i右边最近的小于 height[i] 的高度。那么 结果就是： (right[i] - left[i] -1) * height[i]
     *      关键在于怎么求出  i 左边最近的小于 height[i] 的高度。 使用单调栈。
     *          Time: O(n)          Space: O(n)
     * @param heights
     * @return
     */
    public int largestRectangleArea11(int[] heights) {

        if (heights.length == 0) {
            return 0;
        }
        int max = 0;
        int k;
        int j;
        for (int i = 0; i < heights.length; i++) {
            j = i + 1;
            k = i - 1;
            while (j < heights.length && heights[j] >= heights[i]) {
                j++;
            }
            while (k >= 0 && heights[k] >= heights[i]) {
                k--;
            }
            max = Math.max(max, heights[i] * (j - k - 1));
        }
        return max;

    }

    public int largestRectangleArea12(int[] heights) {

        if (heights.length == 0) {
            return 0;
        }
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? heights.length : stack.peek();
            stack.push(i);
        }

        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] * (right[i] - left[i] - 1));
        }

        return max;
    }


}
