package com.java.on.java8.function.program.currying;

import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

public class CurriedInAdd {

    public static void main(String[] args) {

        IntFunction<IntUnaryOperator> curriedIntAdd = a -> b -> a + b;

        IntUnaryOperator add4 = curriedIntAdd.apply(4);

        System.out.println(add4.applyAsInt(5));
    }

}
