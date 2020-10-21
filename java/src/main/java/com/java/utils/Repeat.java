package com.java.utils;

import java.util.stream.IntStream;

public class Repeat {

    // 替代简单的for循环
    public static void repeat(int n, Runnable runnable) {

        IntStream.range(0, n).forEach(i -> runnable.run());
    }

    static void hi() {
        System.out.println("Hi");
    }

    public static void main(String[] args) {
        repeat(3, () -> System.out.println("Looping"));
        repeat(2, Repeat::hi);
    }
}
