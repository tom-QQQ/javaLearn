package com.java.on.java8.function.program.high.order.function;

import java.util.function.Function;

class I {
    @Override
    public String toString() {
        return "I";
    }
}

class O {

    @Override
    public String toString() {
        return "O";
    }
}

public class TransformFunction {

    // Function接口还有个compose方法, 与andThen调用相反,
    // andThen首先调用本身的apply, 再调用参数的apply
    // compose先调用参数的apply方法, 再调用本身的apply方法
    static Function<I, O> transform(Function<I, O> in) {

        // 这里是andThen时, 传入I, 先调用main的, 打印I, 返回O, 再调用这里的, 打印传入的O, 返回O
        // 这里是compose时, 传入I, 先调用这里的打印I, 返回I, 再调用main的打印传入的I, 返回O
        return in.compose(o -> {
            // 重写andThen调用的in的apply方法, 传入什么打印什么, 返回什么, 返回和打印的类相同
            System.out.println(o);
            return o;
        });
    }

    public static void main(String[] args) {
        // 重写apply方法, 返回Function
        Function<I, O> f2 = transform(i -> {
            // 传入i, 固定返回O在, 打印和返回的不一定相同
            System.out.println(i);
            return new O();
        });

        O o = f2.apply(new I());
    }
}
