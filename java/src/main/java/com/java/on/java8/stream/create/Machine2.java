package com.java.on.java8.stream.create;

import com.java.utils.Operations;

import java.util.Arrays;

public class Machine2 {

    public static void main(String[] args) {
        Arrays.stream(new Operations[]{
                () -> Operations.show("Bing"),
                () -> Operations.show("Crack"),
                () -> Operations.show("Twist"),
                () -> Operations.show("Pop")
        }).forEach(Operations::execute);
    }
}
