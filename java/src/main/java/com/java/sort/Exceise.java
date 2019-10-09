package com.java.sort;

import java.util.*;

/**
 * @author Ning
 * @date Create in 2019/8/17
 */
public class Exceise {

    public static void quickSort(int[] array, int left, int right) {

        if (left >= right) {
            return;
        }

        int pivot = array[left];
        int leftIndex = left;
        int rightIndex = right;

        while (leftIndex != rightIndex) {

            while (array[rightIndex] > pivot && leftIndex < rightIndex) {
                rightIndex--;
            }

            array[leftIndex] = array[rightIndex];

            while (array[leftIndex] < pivot && leftIndex < rightIndex) {
                leftIndex++;
            }

            array[rightIndex] = array[leftIndex];
        }

        array[leftIndex] = pivot;

        quickSort(array, left, leftIndex - 1);
        quickSort(array, leftIndex + 1, right);

    }


    public static void mergeSort(int[] array, int left, int right) {

        while (left >= right) {
            return;
        }

        int mid = (right + left) >> 1;

        mergeSort(array,left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {

        int leftIndex = left;
        int rightIndex = mid + 1;

        int[] temp = new int[right - left + 1];
        int tempIndex = 0;

        while (leftIndex <= mid && rightIndex <= right) {

            if (array[leftIndex] < array[rightIndex]) {
                temp[tempIndex++] = array[leftIndex++];
            } else {
                temp[tempIndex++] = array[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            temp[tempIndex++] = array[leftIndex++];
        }

        while (rightIndex <= right) {
            temp[tempIndex++] = array[rightIndex++];
        }

        System.arraycopy(temp, 0, array, left, temp.length);

    }


    public static void countSort(int[] array) {

        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;

        for (int num : array) {

            if (num > maxValue) {
                maxValue = num;
            } else {
                minValue = num;
            }
        }

        int[] temp = new int[maxValue - minValue + 1];

        for (int num : array) {
            temp[num - minValue]++;
        }

        int arrayIndex = 0;
        for (int index = 0; index < temp.length; index++) {

            for (int times = 0; times < temp[index]; times++) {
                array[arrayIndex++] = index + minValue;
            }
        }
    }

    public static void bucketSort(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }

        int bucketsNum = 4;

        List<List<Integer>> buckets = new ArrayList<>();

        for (int index = 0; index < bucketsNum; index++) {
            buckets.add(new LinkedList<>());
        }

        for (int num : array) {

            buckets.get(bucketFunction(num)).add(num);
        }

        for (List<Integer> list : buckets) {
            Collections.sort(list);
        }

        int arrayIndex = 0;
        for (List<Integer> list : buckets) {
            for (int num : list) {
                array[arrayIndex++] = num;
            }
        }

    }

    private static int bucketFunction(int num) {
        return num / 3;
    }
}
