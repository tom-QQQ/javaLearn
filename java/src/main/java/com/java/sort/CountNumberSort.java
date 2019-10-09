package com.java.sort;

import java.util.Arrays;

/**
 * 计数排序/桶排序, M是原数组长度, N是最大值减去最小值, 时间复杂度O(M), 空间复杂度O(N)
 * @author Ning
 * @date Create in 2019/4/9
 */
public class CountNumberSort {

    private static int[] countSort(int[] array) {

        // 计算缩放数组的长度, 运算量为原始数组长度
        int maxValue = 0;
        int minValue = Integer.MAX_VALUE;

        for (int value : array) {

            if (value > maxValue) {
                maxValue = value;
            }

            if (value < minValue) {
                minValue = value;
            }
        }

        // 构建缩放数组并存入缩放后的值, 运算量为原始数组长度
        // 索引就是缩放后的值, 索引对应的值是缩放值出现的次数
        int arrayLength = maxValue - minValue + 1;
        int[] valueArray = new int[arrayLength];

        for (int value : array) {

            valueArray[value-minValue]++;
        }


        // 构建结果数组, 运算量为原始数组长度
        int[] resultValue = new int[array.length];
        int resultIndex = 0;

        for (int valueIndex = 0; valueIndex < valueArray.length; valueIndex++) {

            for (int index = 0; index < valueArray[valueIndex]; index++) {
                resultValue[resultIndex] = valueIndex+minValue;
                resultIndex++;
            }
        }

        return resultValue;
    }

    public static void main(String[] args) {
        int[] array = new int[] {4,4,6,5,3,2,8,1,7,5,6,0,10};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }
}
