package com.java.on.java8.function.program.lambda.expression;

interface InCall {
    int call(int arg);
}

public class Recursion {

    static InCall fact;

    InCall fib;

    public Recursion() {
        fib = n ->
                n == 0 ? 0 :
                n == 1 ? 1 :
                        fib.call(n - 1) + fib.call(n - 2);
    }

    int fibonacci(int n) {
        return fib.call(n);
    }

    /**
     * 递归阶乘
     */
    public static void recursiveFactorial() {

        // 静态变量实现
        fact = n -> n == 0 ? 1 : n * fact.call(n - 1);
        for (int i = 0; i <= 10; i++) {
            System.out.println(fact.call(i));
        }
    }

    /**
     * 递归斐波那契
     */
    public static void recursiveFibonacci() {

        // 实例变量实现
        Recursion rf = new Recursion();
        for (int i = 0; i <= 10; i++) {
            System.out.println(rf.fibonacci(i));
        }
    }

    public static void main(String[] args) {

        recursiveFactorial();
        recursiveFibonacci();
    }
}
