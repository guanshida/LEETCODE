package com.leetcode.zixue;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.util.*;

/**
 * 重复的，临时写一下。
 * @author guanshida
 * @date 2023/9/5
 */
public class TempTest {

    public static void main(String[] args) {
        TempTest a = new TempTest();

        System.out.println(a.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(a.lengthOfLongestSubstring("bbbbb"));
        System.out.println(a.lengthOfLongestSubstring(""));
        System.out.println(a.lengthOfLongestSubstring("dvdf"));
        System.out.println(a.lengthOfLongestSubstring("abba"));



        System.out.println(a.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));

    }

    /**
     * 把数字放在该放的位置，其中 <=0和>length的，不用管。其他元素使得 nums[i] = i+1
     *  然后遍历一遍，第一个不符合  nums[i] = i+1的就是。
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        int length = nums.length;
        while (i < length) {
            if (nums[i] > 0 && nums[i] <= length && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                int tmp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[tmp - 1] = tmp;
            } else {
                i++;
            }
        }
        for (i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i+1;
            }
        }
        return length+1;
    }
    /**
     * 两遍遍历：第一遍遍历求左边乘积，第二次遍历乘上右边的。
     * Space:O(1)           Time:O(n)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right = right * nums[i + 1];
            res[i] = res[i] * right;
        }
        return res;
    }

    /**
     * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
     * 解法一：搞一个 Map<char,索引>  然后使用双指针遍历一遍即可。
     *          Space: O(char种类)   Time: O(n)
     * 解法二：
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0, right = 0;
        for (; right < s.length(); right++) {
            Character character = s.charAt(right);
            if (map.containsKey(character) && map.get(character) >= left) {
                max = Math.max(max, right - left);
                left = map.get(character) + 1;
            }
            map.put(character, right);
        }
        max = Math.max(max, right - left);
        return max;

    }
    /**
     * https://leetcode.cn/problems/3sum/?envType=study-plan-v2&envId=top-100-liked
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> tmp = new HashSet<>();
        Arrays.sort(nums);
        nSum(nums, 3, 0,0, res, tmp);

        return res;
    }

    private void nSum(int[] nums, int i, int startIndex, int target, List<List<Integer>> res, Set<Integer> midIndexSet) {
        if (i == 2) {
            twoSum(nums, startIndex, target, res, midIndexSet);
            return;
        }
        // i>2
        for (int j = 0; j < nums.length - 2; j++) {
            midIndexSet.add(j);
            nSum(nums, i - 1, j + 1, target - nums[j], res, midIndexSet);
            midIndexSet.remove(j);
        }

    }

    private void twoSum(int[] nums, int startIndex, int target, List<List<Integer>> res, Set<Integer> midIndexSet) {
        int i = startIndex;
        int j = nums.length - 1;

        while (i < j) {
            int tmpTarget = nums[i] + nums[j];
            if (tmpTarget > target) {
                j--;
            } else if (tmpTarget < target) {
                i++;
            } else {
                // 找到了，如果栈中没有，则加到结果集中。
                if (!midIndexSet.contains(i) && !midIndexSet.contains(j)) {
                    List<Integer> list = new ArrayList<>();
                    midIndexSet.forEach(x -> list.add(nums[x]));
                    list.add(nums[i]);
                    list.add(nums[j]);
                    res.add(list);
                }
                if (nums[i + 1] == nums[i]) {
                    i++;
                } else if (nums[j - 1] == nums[j]) {
                    j--;
                } else {
                    i++;
                    j--;
                }
            }
        }
    }
}
