package com.java.on.java8.function.program.high.order.function;

import java.util.function.Function;

class One {}
class Two {}

public class ConsumeFunction {

    // 消费函数
    static Two consume(Function<One, Two> oneTwo) {

        return oneTwo.apply(new One());
    }

    public static void main(String[] args) {
        Two two = consume(one -> new Two());
    }
}
