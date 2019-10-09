package com.java.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ning
 * @date Create in 2019/4/10
 */
public class  SortFunction {

    /**
     * 选择排序, 从小到大, 时间复杂度O(n^2), 空间复杂度O(n)
     * @param array 需要排序的数组
     */
    public static void selectSort(int[] array) {

        if (array.length < 2) {
            return;
        }

        for (int outerIndex = 0; outerIndex < array.length; outerIndex++) {

            // 初始值定义为外循环索引对应的值
            int minValueIndex = outerIndex;
            for (int innerIndex = outerIndex+1; innerIndex < array.length; innerIndex++) {

                // 找出最小值的索引
                if (array[innerIndex] < array[minValueIndex]) {
                    minValueIndex = innerIndex;

                }
            }

            // 最小值的索引和比较值不同时, 交换位置
            if (minValueIndex != outerIndex) {
                int minValue = array[minValueIndex];
                array[minValueIndex] = array[outerIndex];
                array[outerIndex] = minValue;
            }

        }
    }

    /**
     * 冒泡排序, 时间复杂度O(N^2), 空间复杂度O(n)
     * @param array 需要排序的数组
     */
    public static void bubbleSort(int[] array) {

        for (int outerIndex = 0; outerIndex < array.length; outerIndex++) {


            for (int innerIndex = outerIndex+1; innerIndex < array.length; innerIndex++) {

                if (array[outerIndex] > array[innerIndex]) {

                    int value = array[innerIndex];
                    array[innerIndex] = array[outerIndex];
                    array[outerIndex] = value;
                }
            }
        }
    }

    public static void quickSort(int[] array) {

        quickSort(array, 0, array.length-1);
    }

    /**
     * 快速排序, 从小到大, 平均时间复杂度O(nlogn)
     * @param array 需要排序的数组
     * @param left 左边索引
     * @param right 右边索引
     */
    private static void quickSort(int[] array, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivot = array[left];

        int leftIndex = left;
        int rightIndex = right;

        while (leftIndex != rightIndex) {

            // 参考值从左边选取, 这里要优先循环右边, 否则会造成即使参考值不是最大的, 参考值也会被移动到最右边的情况,
            while (array[rightIndex] >= pivot && leftIndex < rightIndex) {
                rightIndex--;
            }

            // 已经记录了基准值, 这里将小于基准值的赋值到左边
            array[leftIndex] = array[rightIndex];

            while (array[leftIndex] <= pivot && leftIndex < rightIndex) {
                leftIndex++;
            }

            // 已经记录了基准值, 将大于基准值的赋值到右边
            array[rightIndex] = array[leftIndex];
        }

        // 当leftIndex=rightIndex时, 将基准值赋值到相遇点
        array[leftIndex] = pivot;

        // 递归处理左边和右边的
        quickSort(array, left, leftIndex - 1);
        quickSort(array, leftIndex + 1, right);
    }


    /**
     * 堆排序 如果想升序排序就使用大顶堆(完全二叉树满足双亲结点大于等于孩子结点)，反之使用小顶堆。原因是堆顶元素需要交换到序列尾部
     * @param array 数组
     */
    public static void heapSort(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }

        // 建立大顶堆
        for (int i = array.length >> 1; i >= 0; i--) {
            heapAdjust(array, i, array.length - 1);
        }

        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            heapAdjust(array, 0, i-1);
        }

    }

    /**
     * 调整堆, 虽然是分步调整的但是实际上每次调整的部分都是所有元素构成的树中的一部分, 而不是对取出的元素重新进行堆构建后调整的
     * 如果输入数据为6, 5, 9, 3, 2, 1, 则构成的堆为:
     *      6
     *    5   9
     *   3 2 1
     * @param array 数组
     * @param start 数组开始索引
     * @param end 数组结束索引
     */
    private static void heapAdjust(int[] array, int start, int end) {

        int temp = array[start];

        //左右孩子的节点分别为2*i+1,2*i+2
        for (int i = 2*start+1; i <= end; i*= 2) {

            // 选出左右孩子较小的下标
            if (i < end && array[i] < array[i+1]) {
                i++;
            }

            if (temp >= array[i]) {
                // 已为最大顶堆, 保持稳定性
                break;
            }

            // 将子节点上移
            array[start] = array[i];
            // 下一轮筛选
            start = i;

        }

        // 插入正确的位置
        array[start] = temp;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 归并排序, 时间复杂度O(nlogn) 空间复杂度O(n)
     * @param array 需要排序的数据
     * @param left 左指针
     * @param right 右指针
     */
    public static void mergeSort(int[] array, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = (left+right) >> 1;

        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, mid, right);

    }

    /**
     * 合并数组
     * @param array 数组
     * @param left 左索引
     * @param mid 中间索引
     * @param right 右索引
     */
    private static void merge(int[] array, int left, int mid, int right) {

        int[] temp = new int[right - left + 1];

        int leftPartIndex = left;
        int rightPartIndex  = mid + 1;
        int tempArrayIndex = 0;

        // 左右两边数组的值按顺序两两相比, 选择较小值的复制到临时数组中, 并让索引加1
        while (leftPartIndex <= mid && rightPartIndex <= right) {

            if (array[leftPartIndex] <= array[rightPartIndex]) {
                temp[tempArrayIndex++] = array[leftPartIndex++];

            } else {
                temp[tempArrayIndex++] = array[rightPartIndex++];
            }
        }

        // 右侧的数组数据全部复制到临时数组中后, 由于左侧数组中的数据已经是顺序的, 可以按顺序将左侧数组中剩余的元素全部复制到临时数组
        while (leftPartIndex <= mid) {
            temp[tempArrayIndex++] = array[leftPartIndex++];
        }

        // 左侧数组的全部数据复制到临时数组中后, 由于右侧数组中的数据已经是顺序的, 可以按顺序将右侧数组中剩余的元素全部复制到临时数组
        while (rightPartIndex <= right) {
            temp[tempArrayIndex++] = array[rightPartIndex++];
        }

        // 将排序完成的临时数组的值重新复制到目标数组的对应位置中去
        System.arraycopy(temp, 0, array, left, temp.length);
    }


    /**
     * 希尔排序 当n在某个范围内时, 时间复杂度O(n^1.3)
     * @param array 需要排序的数组
     */
    public static void shellSort(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }

        int d = array.length >> 1;

        while (d >= 1) {
            shellInsert(array, d);
            d >>= 1;
        }

    }

    /**
     * 希尔排序的一趟插入
     * @param array 数组
     * @param d 增量
     */
    private static void shellInsert(int[] array, int d) {

        for (int index = d; index < array.length; index++) {

            int j = index - d;
            int temp = array[index];

            while (j >= 0 && array[j] > temp) {
                array[j+d] = array[j];
                j -= d;
            }

            if (j != index - d) {
                array[j+d] = temp;
            }
        }
    }

    public static void countSort(int[] array) {

        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;

        for (int index = 0; index < array.length; index++) {

            if (array[index] > maxValue) {
                maxValue = array[index];
            }

            if (array[index] < minValue) {
                minValue = array[index];
            }
        }

        int[] temp = new int[maxValue - minValue + 1];

        for (int index = 0; index < array.length; index++) {
            temp[array[index]-minValue]++;
        }

        int arrayIndex = 0;
        for (int index = 0; index < temp.length; index++) {

            for (int value = 0; value < temp[index]; value++) {
                array[arrayIndex++] = index+minValue;
            }
        }
    }

    /**
     * 桶排序, 时间复杂度O(N + C) C = N(logN - logM) 空间复杂度: O(N + M)
     * @param array 需要排序的数组
     */
    public static void bucketSort(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }

        // 桶的数量, 取决于待排序值的范围
        int bucketsNum = 4;

        List<List<Integer>> buckets = new ArrayList<>();

        // 将待排序的值放入多个桶中
        for (int index = 0; index < bucketsNum; index++) {
            buckets.add(new LinkedList<>());
        }

        // 将待排序的值根据映射函数放到不同的桶中
        for (int num : array) {
            buckets.get(bucketFunction(num)).add(num);
        }

        // 使用快排排序各个桶中的值
        for (List<Integer> list : buckets) {
            Collections.sort(list);
        }

        // 将排序完成的结果还原到原数组中去
        int arrayIndex = 0;
        for (List<Integer> list : buckets) {
            for (int num : list) {
                array[arrayIndex++] = num;
            }
        }

    }

    /**
     * 待排序的值到桶的映射函数
     * @param num 待排序的值
     * @return 桶的索引
     */
    private static int bucketFunction(int num) {
        return num / 3;
    }
}
