package com.java.on.java8.function.program.function.composition;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateComposition {

    static Predicate<String>
    p1 = s -> s.contains("bar"),
    p2 = s -> s.length() < 5,
    p3 = s -> s.contains("foo"),
    // (!p1 && p2) || p3
    p4 = p1.negate().and(p2).or(p3);

    // (!p1 || p3) && p2
//    p4 = p1.negate().or(p3).and(p2);

    public static void main(String[] args) {
        Stream.of("bar", "foobar", "foobar2", "fongopuckey").filter(p4).forEach(System.out::println);
    }
}
