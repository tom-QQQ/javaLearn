package com.java.on.java8.stream.create;

import java.util.Random;
import java.util.stream.Stream;

public class RandomGenerators {

    public static <T> void show(Stream<T> stream) {
        stream
                .limit(4)
                .forEach(System.out::println);
        System.out.println("++++++++");
    }

    // 随机数流
    public static void main(String[] args) {


        Random rand = new Random(47);
        // box实现方法mapToObj(Integer/Long/Double::valueOf), 会根据不同数据类型有不同的实现
        show(rand.ints().boxed());
        show(rand.longs().boxed());
        show(rand.doubles().boxed());

        // 控制上限和下限
        show(rand.ints(10, 20).boxed());
        show(rand.longs(50, 200).boxed());
        show(rand.doubles(20, 30).boxed());

        // 控制流大小
        show(rand.ints(2).boxed());
        show(rand.longs(2).boxed());
        show(rand.doubles(2).boxed());

        // 控制流的大小和界限
        show(rand.ints(3, 3, 9).boxed());
        show(rand.longs(3, 12, 22).boxed());
        show(rand.doubles(3, 11.5, 12.3).boxed());
    }
}
