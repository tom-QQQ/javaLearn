package com.java.leet.code;

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

        if (nums1.length < nums2.length) {

            int[] num3 = nums1;
            nums1 = nums2;
            nums2 = num3;
        }

        List<Integer> middleValues = getMiddleValues(nums1);

        if (middleValues.size() == 1) {

            // 指定值的索引也是顺序数组中小于指定值的数字数量
            int valueIndex = Utils.findTargetIndex(nums2, middleValues.get(0), false, false);

            // 仅考虑需要查找的值不重复的情况, 有下面的结论, 并且大于需要查找值的数量和小于需要查找值的数量均包含需要查找值(如果数组
            // 中存在指定值的话)
            int lessThanMiddleValueNum = nums2.length - Utils.findTargetIndex(nums2, middleValues.get(0), false, true);

            // 另外一个数组中大于第一个数组中位数的数量和小于的数量相同, 则2数组合并后的中位数是第一个数组的中位数
            if (valueIndex == lessThanMiddleValueNum) {

                return middleValues.get(0);
            }

            if (valueIndex > lessThanMiddleValueNum) {
                int moreThanCounts = valueIndex - lessThanMiddleValueNum;

            }

        }



        return 0.1;
    }

//    private List<Integer> getNums(int[] num, boolean isBig) {
//
//        List<Integer> list
//    }

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

        int[] num1 = new int[]{1,2,3};
        int[] num2 = new int[]{1,2,4,5};


        int valueIndex = Utils.findTargetIndex(num2, 3, false, false);
        System.out.println(valueIndex);

        //        MedianNum medianNum = new MedianNum();
//        System.out.println(medianNum.findMedianSortedArrays(num1, num2));

//        int valueIndex = Utils.findTargetIndex(num2, 3);

    }
}
