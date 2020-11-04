package com.java.on.java8.function.program.lambda.interfaces;

import java.util.Comparator;
import java.util.function.*;

class AA {}
class BB {}
class CC {}

/**
 * 基于类的函数式接口
 */
public class ClassFunctional {

    static AA f1() {return new AA();}
    static int f2(AA aa1, AA aa2) { return 1;}
    static void f3(AA aa) {}
    static void f4(AA aa, BB bb) {}
    static CC f5(AA aa) { return new CC();}
    static CC f6(AA aa, BB bb) { return new CC();}
    static boolean f7(AA aa) {return true;}
    static boolean f8(AA aa, BB bb) {return true;}
    static AA f9(AA aa) {return new AA();}
    static AA f10(AA aa1, AA aa2) {return new AA();}

    public static void main(String[] args) {

        // 无参数返回对象
        Supplier<AA> s = ClassFunctional::f1;
        s.get();

        // 2个同类对象参数, 返回int, 比较接口
        Comparator<AA> c = ClassFunctional::f2;
        c.compare(new AA(), new AA());

        // 对象参数返回空
        Consumer<AA> cons = ClassFunctional::f3;
        cons.accept(new AA());

        // 2个不同对象返回空
        BiConsumer<AA, BB> bicons = ClassFunctional::f4;
        bicons.accept(new AA(), new BB());

        // 第一个泛型为参数, 第二个泛型为返回类型
        Function<AA, CC> f = ClassFunctional::f5;
        CC cc = f.apply(new AA());

        // 前2个泛型为参数类型, 第三个泛型为返回值类型
        BiFunction<AA, BB, CC> bif = ClassFunctional::f6;
        cc = bif.apply(new AA(), new BB());

        // 对象类型返回boolean
        Predicate<AA> p = ClassFunctional::f7;
        boolean result = p.test(new AA());

        // 2不同对象类型返回boolean
        BiPredicate<AA, BB> bip = ClassFunctional::f8;
        result = bip.test(new AA(), new BB());

        // 同一对象类型既是参数类型, 也是返回类型
        UnaryOperator<AA> uo = ClassFunctional::f9;
        AA aa = uo.apply(new AA());

        // 2个同一参数类型的对象返回该对象的类型
        BinaryOperator<AA> bo = ClassFunctional::f10;
        aa = bo.apply(new AA(), new AA());

    }
}
