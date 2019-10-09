package com.java.leet.code;

import java.util.Arrays;

/**
 * @author Ning
 * @date Create in 2019/10/5
 */
public class Utils {

    /**
     * 二分法查找数组中指定的值, 没有时返回-1
     * @param nums 升序数组
     * @param target 目标值
     * @return 目标值索引, 没有查到时返回-1
     */
    public static Integer findTargetIndex(int[] nums, int target) {

        int leftIndex = 0;
        int rightIndex =  nums.length - 1;

        while (leftIndex <= rightIndex) {

            int midIndex = (rightIndex + leftIndex) >>> 1;

            int value = nums[midIndex];

            if (value > target) {
                rightIndex = midIndex - 1;

            } else if (value < target) {
                leftIndex = midIndex + 1;

            } else {
                return midIndex;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.binarySearch(ints, 999));
    }
}
