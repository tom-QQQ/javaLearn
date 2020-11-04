package com.java.on.java8.function.program.high.order.function;

import java.util.function.Function;

interface   FuncSS extends Function<String, String> {}

/**
 * 高阶函数（Higher-order Function）, 一个消费或产生函数的函数
 */
public class ProduceFunction {

    // 这是一个高阶函数, 产生函数
    static FuncSS produce() {
        return s -> s.toLowerCase();
    }

    public static void main(String[] args) {
        FuncSS f = produce();
        System.out.println(f.apply("YELLING"));
    }
}
