package com.java.data.structure.recursive;

/**
 * @author Ning
 * @date Create in 2019/4/10
 */
public class Recursive {


    /**
     * 递归求和
     * @param value 需要求和的数
     * @return 求和结果 1+2+3+...+value
     */
    public static int sumNumber(int value) {

        if (value == 0) {
            return 0;
        }

        return sumNumber(value-1) + value;
    }

    /**
     * 递归数组求和
     * @param array 数组
     * @param arrayLength 数组长度
     * @return 求和结果
     */
    public static int sumArray(int[] array, int arrayLength) {

        // 后面返回的是arrayLength-1, 这里以1为返回条件
        if (arrayLength == 1) {
            return array[0];
        }

        return sumArray(array, arrayLength-1) + array[arrayLength-1];
    }


    /**
     * 递归查询最大值, 递归完全展开是索引为0的数和int最小值比较, 较大的值再和之后的索引对应值比较
     * @param array 数组
     * @param arrayLength 数组长度
     * @return 最大值
     */
    public static int findMaxValue(int[] array, int arrayLength) {

        if (arrayLength <= 0) {
            return Integer.MIN_VALUE;
        }

        return array[arrayLength - 1] > findMaxValue(array, arrayLength-1) ? array[arrayLength] : findMaxValue(array, arrayLength - 1);
    }
}
