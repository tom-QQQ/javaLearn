package com.java.leet.code.median;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2019/10/11
 */
public class MedianNum {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if (nums1.length == 0) {
            return calculateMiddleValue(nums2);

        } else if (nums2.length == 0) {
            return calculateMiddleValue(nums1);
        }

        int[] resultIndex;
        int totalLength = nums1.length+nums2.length;
        if (totalLength % 2 == 0) {
            resultIndex = new int[2];
            resultIndex[0] = totalLength/2-1;
            resultIndex[1] = totalLength/2;
        } else {
            resultIndex = new int[1];
            resultIndex[0] = totalLength/2;
        }

        int result;
        int result2 = 0;
        int num = -1;
        int index1 = -1;
        int index2 = -1;
        while (true) {

            int indexTemp1 = index1 + 1;
            int indexTemp2 = index2 + 1;


            if (nums1.length <= indexTemp1) {
                index2++;
                result = nums2[indexTemp2];

            } else if (nums2.length <= indexTemp2) {
                index1++;
                result = nums1[indexTemp1];
            } else if (nums1[indexTemp1] < nums2[indexTemp2]) {
                index1++;
                result = nums1[indexTemp1];

            } else {
                index2++;
                result = nums2[indexTemp2];
            }
            num++;

            if (resultIndex.length == 1 && num == resultIndex[0]) {
                return result;
            }

            if (resultIndex.length == 2 && num == resultIndex[0]) {
               result2 = result;
            }

            if (resultIndex.length == 2 && num == resultIndex[1]) {
                return divided(result, result2);
            }
        }
    }

    private static double divided(int num1, int num2) {

        return (num1 + num2) / 2.0;
    }

    /**
     * 获取已排序数组中位数值
     * @param nums 数组
     * @return 中位数值
     */
    private double calculateMiddleValue(int[] nums) {

        List<Integer> middleValues = getMiddleValues(nums);

        if (middleValues.size() == 1) {
            return (double) middleValues.get(0);
        }

        return ((double) (middleValues.get(0) + middleValues.get(1)))/2;
    }

    /**
     * 获取已排序数组中间的值, 可能有2个
     * @param nums 数组
     * @return 中间的值
     */
    private List<Integer> getMiddleValues(int[] nums) {

        int length = nums.length;

        List<Integer> middleValues = new ArrayList<>();
        if (length % 2 == 0) {
            middleValues.add(nums[length/2-1]);
        }
        middleValues.add(nums[length/2]);

        return middleValues;
    }


    /**
     * 特殊二分法查询大于等于或小于等于指定数的索引
     * @param nums 数组
     * @param target 目标值
     * @param isLess 小于等于还是大于等于
     * @return
     */
    public static int findNotInclude(int[] nums, int target, boolean isLess) {

        int rightIndex, leftIndex;
        leftIndex = 0;
        rightIndex = nums.length - 1;

        while (leftIndex <= rightIndex) {

            int midIndex = (rightIndex + leftIndex) >>> 1;

            int value = nums[midIndex];

            if (value > target) {
                rightIndex = midIndex - 1;

            } else if (value < target) {
                leftIndex = midIndex + 1;

            } else {
                if (isLess) {
                    leftIndex = midIndex + 1;
                } else {
                    rightIndex = midIndex - 1;
                }
            }
        }

        if (isLess) {
            return rightIndex;
        } else {
            return leftIndex;
        }

    }

    public static void main(String[] args) {

        int[] num1 = new int[]{0,0};
        int[] num2 = new int[]{0,0};

        MedianNum medianNum = new MedianNum();
        System.out.println(medianNum.findMedianSortedArrays(num1, num2));

    }
}
