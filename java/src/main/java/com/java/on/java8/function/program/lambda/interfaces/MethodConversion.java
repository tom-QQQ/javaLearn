package com.java.on.java8.function.program.lambda.interfaces;

import java.util.function.BiConsumer;

class In1 {}
class In2 {}

public class MethodConversion {

    static void accept(In1 in1, In2 in2) {
        System.out.println("accept()");
    }

    static void someOtherName(In1 in1, In2 in2) {
        System.out.println("someOtherName()");
    }

    public static void main(String[] args) {
        BiConsumer<In1, In2> bic;

        bic = MethodConversion::accept;
        bic.accept(new In1(), new In2());

        // 只要返回类型和方法参数相同即可进行方法引用
        bic = MethodConversion::someOtherName;
        // 调用时使用函数式接口的方法名进行调用
        bic.accept(new In1(), new In2());
    }
}
