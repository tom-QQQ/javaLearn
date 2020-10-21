package com.java.on.java8.stream.create;

import java.util.stream.Stream;

public class Fibonacci {

    int x = 0;

    Stream<Integer> numbers() {
        // 流的第一个元素作为第一个参数, 第一个元素传到第二个参数中进行计算, 计算的结果作为第二个元素
        return Stream.iterate(1, i -> {
           int result = x + i;
           x = i;
           return result;
        });
    }

    public static void main(String[] args) {
        new Fibonacci().numbers()
                // 过滤前20个
                .skip(20)
                .limit(10)
                .forEach(System.out::println);
    }
}
