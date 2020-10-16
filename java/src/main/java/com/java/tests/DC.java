package com.java.tests;

public class DC {

    public static int sum(int[] arrays) {

        if (arrays.length == 1) {
            return arrays[0];
        }

        int[] temp = new int[arrays.length-1];
        System.arraycopy(arrays, 1, temp, 0, arrays.length-1);
        return arrays[0] + sum(temp);
    }

    public static void main(String[] args) {

        System.out.println(sum(new int[]{1, 2, 3, 4, 5, 6}));
    }
}
