package com.java.on.java8.function.program.lambda.interfaces;

import java.util.function.*;

class Foo { }

class Bar {
    Foo f;
    Bar(Foo f) {
        this.f = f;
    }
}

class IBaz {
    int i;
    IBaz(int i) {
        this.i = i;
    }
}

class LBaz {
    long l;
    LBaz(long l) {
        this.l = l;
    }
}

class DBaz {
    double d;
    DBaz(double d) {
        this.d = d;
    }
}

/**
 * Function变体, 用到了java提供的函数式接口
 */
public class FunctionVariants {

    // 泛型第一个为参数, 第二个为返回类型
    static Function<Foo, Bar> f1 = f -> new Bar(f);

    // int/long/double参数, 返回类型为对象
    static IntFunction<IBaz> f2 = f -> new IBaz(f);
    static LongFunction<LBaz> f3 = f -> new LBaz(f);
    static DoubleFunction<DBaz> f4 = f -> new DBaz(f);

    // 参数类型为对象, 返回类型为int/long/double
    static ToIntFunction<IBaz> f5 = f -> f.i;
    static ToLongFunction<LBaz> f6 = f -> f.l;
    static ToDoubleFunction<DBaz> f7 = f -> f.d;

    // int -> long
    static IntToLongFunction f8 = i -> i;
    // int -> double
    static IntToDoubleFunction f9 = i -> i;
    // long -> int
    static LongToIntFunction f10 = i -> (int) i;
    // long -> double
    static LongToDoubleFunction f11 = i -> i;
    // double -> int
    static DoubleToIntFunction f12 = i -> (int) i;
    // double -> long
    static DoubleToLongFunction f13 = i -> (long) i;

    public static void main(String[] args) {

        Bar b = f1.apply(new Foo());

        IBaz ib = f2.apply(11);
        LBaz lb = f3.apply(11);
        DBaz db = f4.apply(11);

        int i = f5.applyAsInt(ib);
        long l = f6.applyAsLong(lb);
        double d = f7.applyAsDouble(db);

        l = f8.applyAsLong(12);
        d = f9.applyAsDouble(12);

        i = f10.applyAsInt(12);
        d = f11.applyAsDouble(12);

        i = f12.applyAsInt(12);
        l = f13.applyAsLong(12);


    }
}
