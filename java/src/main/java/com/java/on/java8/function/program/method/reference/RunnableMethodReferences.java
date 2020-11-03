package com.java.on.java8.function.program.method.reference;

class Go {
    static void go() {
        System.out.println("Go::go");
    }
}

public class RunnableMethodReferences {

    public static void main(String[] args) {

        // 完整写法
        new Thread(new Runnable() {
            public void run() {
                System.out.println("Anonymous");
            }
        }).start();

        // lambda表达式
        new Thread(() -> System.out.println("lambda")).start();

        // 方法引用
        new Thread(Go::go).start();
    }
}
