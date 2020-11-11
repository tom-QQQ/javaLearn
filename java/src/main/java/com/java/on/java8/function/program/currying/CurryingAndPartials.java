package com.java.on.java8.function.program.currying;

import java.util.function.Function;

public class CurryingAndPartials {

    static String uncurried(String a, String b) {
        return a + b;
    }

    public static void main(String[] args) {

        // 柯里化函数
        Function<String,Function<String, String>> sum =
                a -> b -> a + b;

        System.out.println(uncurried("Hi ", "Ho"));

        Function<String, String> hi = sum.apply("Hi ");
        System.out.println(hi.apply("Ho"));

        //
        Function<String, String> sumHi =
                sum.apply("Hup ");
        System.out.println(sumHi.apply("Ho "));
        System.out.println(sumHi.apply("Hey "));
    }
}
