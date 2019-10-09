package com.java.data.structure.binary.search;

/**
 * @author Ning
 * @date Create in 2019/4/9
 */
public class BinaryFind {

    /**
     * 二分法求值是否存在
     * @param array 按顺序排列的数组
     * @param value 指定值
     * @return 返回值与指定值相同时存在,返回-1时不存在
     */
    public static int findByBinary(int[] array, int value) {


        int startIndex = 0;
        int endIndex = array.length - 1;

        while (startIndex <= endIndex) {

            int refIndex = (startIndex + endIndex) >> 1;
            if (array[refIndex] == value) {
                return value;
            }

            if (value > array[refIndex]) {
                // 目标值大于指定值, 起始索引向
                startIndex = refIndex + 1;
                continue;
            }

            if (value < array[refIndex]) {
                endIndex = refIndex - 1;
            }
        }
        return -1;
    }
}
