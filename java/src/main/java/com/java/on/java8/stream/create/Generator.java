package com.java.on.java8.stream.create;

import com.java.on.java8.stream.Bubble;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator implements Supplier<String> {

    Random rand = new Random(47);

    char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    @Override
    public String get() {
        return String.valueOf(letters[rand.nextInt(letters.length)]);
    }

    public static void main(String[] args) {

        String word = Stream.generate(new Generator()).limit(30).collect(Collectors.joining());
        System.out.println(word);

        duplicator();

        duplicatorBubbles();
    }

    public static void duplicator() {
        Stream.generate(() -> "duplicate")
                .limit(3)
                .forEach(System.out::println);
    }

    public static void duplicatorBubbles() {
        Stream.generate(Bubble::bubbler)
                .limit(5)
                .forEach(System.out::println);
    }
}
