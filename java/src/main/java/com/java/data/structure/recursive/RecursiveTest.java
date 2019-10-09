package com.java.data.structure.recursive;

/**
 * @author Ning
 * @date Create in 2019/4/10
 */
public class RecursiveTest {

    public static void main(String[] args) {

        System.out.println(Recursive.sumNumber(5));

        System.out.println(Recursive.sumArray(new int[]{1, 2, 3, 4}, 4));

        System.out.println(Recursive.findMaxValue(new int[]{1, 2, 3, 4}, 4));
    }
}
