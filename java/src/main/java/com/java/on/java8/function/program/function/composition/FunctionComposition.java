package com.java.on.java8.function.program.function.composition;

import java.util.function.Function;

/**
 * 函数组合
 */
public class FunctionComposition {

    static Function<String, String>

    f1 = s -> {
        System.out.println("2");
//        System.out.println(s);
        return s.replace('A', '_');
    },

    f2 = s -> {
        System.out.println("1");
//        System.out.println(s);
        return s.substring(3);
    },

    f3 = s -> {
        System.out.println("3");
//        System.out.println(s);
        return s.toLowerCase();},

    // 先执行f2, 再执行f1, 最后执行f3
    f4 = f1.compose(f2).andThen(f3);

    public static void main(String[] args) {
        System.out.println(f4.apply("GO AFTER ALL AMBULANCES"));
    }
}
