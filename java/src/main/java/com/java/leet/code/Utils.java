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
    public static int findTargetIndex(int[] nums, int target) {

        return findTargetIndex(nums, target, true, true);
    }

    /**
     * 查询目标值的索引, 目标值不存在时, 根据isBig参数判断返回小于目标值的值的索引还是大于的索引
     * @param nums 数组
     * @param target 目标值
     * @param isExact 是否需要精确匹配, true时目标值不存在返回-1, 否则返回索引
     * @param isBig 目标值不存在时, 指定需要返回大于目标值的最小值索引还是小于目标值的最大索引
     * @return 索引
     */
    public static int findTargetIndex(int[] nums, int target, boolean isExact, boolean isBig) {

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

        if (isExact) {
            return -1;
        }

        if (isBig) {
            return leftIndex;
        } else  {
            return rightIndex;
        }

    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.binarySearch(ints, 999));
    }
}
