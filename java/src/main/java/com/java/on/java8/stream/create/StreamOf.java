package com.java.on.java8.stream.create;

import com.java.on.java8.stream.Bubble;

import java.util.stream.Stream;

/**
 * 数组转流
 */
public class StreamOf {

    public static void main(String[] args) {

        // 使用Stream.of()将一组元素转换成流
        Stream.of(new Bubble(1), new Bubble(2), new Bubble(3))
                .forEach(System.out::println);

        Stream.of("It's", "a", "wonderful", "day", "for", "pie!")
                .forEach(System.out::println);

        System.out.println("");

        Stream.of(3.14159, 2.718, 1.618)
                .forEach(System.out::println);
    }
}
