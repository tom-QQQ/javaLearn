package com.java.on.java8.function.program.currying;

import java.util.function.Function;

public class Curried3rdArgs {


    public static void main(String[] args) {

        Function<String,
                Function<String,
                        Function<String, String>>> sum =
                a -> b -> c -> a + b + c;

        Function<String, Function<String, String>> hi =
                sum.apply("Hi ");

        Function<String, String> ho =
                hi.apply("Ho ");

        System.out.println(ho.apply("Hup "));
    }
}
