package com.java.on.java8.function.program.lambda.interfaces;

/**
 * 每个接口只包含一个方法
 * 使用@FunctionalInteface强制执行函数方法模式
 */
@FunctionalInterface
interface Functional {
    String goodbye(String args);
}

interface FunctionalNoAnn {
    String goodbye(String args);
}



public class FunctionalAnnotation {

    public String goodbye(String args) {
        return "Goodbye" + args;
    }

    public static void main(String[] args) {

        FunctionalAnnotation fa = new FunctionalAnnotation();
        Functional f = fa::goodbye;
        FunctionalNoAnn fna = fa::goodbye;
        Functional fl = a -> "Goodbye" + a;
        FunctionalNoAnn fnal = a -> "Goodbye" + a;
    }
}
