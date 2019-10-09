package com.java.sort;

import com.alibaba.fastjson.JSONObject;
import com.java.utils.Array;
import sun.security.action.GetBooleanAction;

import java.security.AccessController;
import java.util.*;

/**
 * @author Ning
 * @date Create in 2019/4/10
 */
public class SortTest {

    public static void main(String[] args) {

        int[] array = new int[]{6, 5, 9, 3, 2, 1};
//        Integer[] arrays = new Integer[]{6, 5, 9, 3, 2, 1};
//        SortFunction.selectSort(array);
//
//        Array.printArray(array);
//
//        array = new int[]{6, 5, 9, 3, 2, 1};
//        SortFunction.bubbleSort(array);
//        Array.printArray(array);

//        SortFunction.quickSort(array);
//        Array.printArray(array);
//
//        SortFunction.heapSort(array);
//        Array.printArray(array);

//        SortFunction.shellSort(array);
//        Array.printArray(array);

//        SortFunction.countSort(array);
//        Array.printArray(array);

        Exceise.quickSort(array, 0, array.length-1);
        Array.printArray(array);

//        Arrays.sort(array);
//        Arrays.sort(arrays);


    }
}
