package com.java.on.java8.stream;

public class Bubble {
    public final int i;

    public Bubble(int n) {
        i = n;
    }

    @Override
    public String toString() {
        return "Bubble(" + i + ")";
    }

    private static int count = 0;

    /**
     * 静态生成器(Static generator), 与 Supplier<Bubble> 是接口兼容的
     * @return
     */
    public static Bubble bubbler() {
        return new Bubble(count++);
    }
}