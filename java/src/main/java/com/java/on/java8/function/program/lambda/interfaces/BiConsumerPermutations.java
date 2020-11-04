package com.java.on.java8.function.program.lambda.interfaces;

import java.util.function.BiConsumer;

public class BiConsumerPermutations {

    static BiConsumer<Integer, Double> bicid = (i, d) ->
            System.out.format("%d, %f%n", i, d);

    static BiConsumer<Double, Integer> bicidi = (d, i) ->
            System.out.format("%d, %f%n", i, d);

    static BiConsumer<Integer, Long> bicil = (i, l) ->
            System.out.format("%d, %d%n", i, l);

    public static void main(String[] args) {
        bicid.accept(47, 11.34);
        bicidi.accept(22.45, 92);
        bicil.accept(1, 11L);
    }
}
