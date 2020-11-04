package com.java.on.java8.function.program.lambda.interfaces;

import java.util.function.Function;
import java.util.function.IntToDoubleFunction;

public class FunctionalWithWrapped {

    public static void main(String[] args) {
        Function<Integer, Double> fid = i -> (double) i;
        IntToDoubleFunction fid2 = i -> i;
    }
}
