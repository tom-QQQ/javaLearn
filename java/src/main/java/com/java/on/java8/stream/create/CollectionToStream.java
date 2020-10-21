package com.java.on.java8.stream.create;

import com.java.on.java8.stream.Bubble;

import java.util.*;

/**
 * 集合转流
 */
public class CollectionToStream {

    public static void main(String[] args) {

        // 集合通过stream()方法转换成流
        List<Bubble> bubbles = Arrays.asList(new Bubble(1), new Bubble(2), new Bubble(3));

        System.out.println(bubbles.stream()
                .mapToInt(b -> b.i)
                .sum());

        Set<String> w = new HashSet<>(Arrays.asList("It's a wonderful day for pie!".split(" ")));
        w.stream()
                .map(x -> x + " ")
                .forEach(System.out::print);
        System.out.println();

        Map<String, Double> m = new HashMap<>();
        m.put("pi", 3.141);
        m.put("e", 2.718);
        m.put("phi", 1.618);
        m.entrySet().stream()
                .map(e -> e.getKey().concat(":").concat(String.valueOf(e.getValue())))
                .forEach(System.out::println);
    }
}