package com.java.on.java8.function.program.method.reference.unbound;

class X {
    String f(String a) {
        return "X::f()" + a;
    }
}

interface MakeString {
    String make(String a);
}

interface TransformX {
    String transform(X x, String a);
}

/**
 * TransformX tx = X::f
 * 实际类似创建了一个类似以下代码的类
 */
class A implements TransformX {

    @Override
    public String transform(X x, String a) {
        return x.f(a);
    }
}

/**
 * 未绑定的方法引用
 */
public class UnboundMethodReferences {

    public static void main(String[] args) {

        // X类的f方法无法传入对象, 不能进行方法引用, 可以这样 MakeString ms = (a) -> new X().f(a);
        // MakeString ms = X::f;


        // 使用未绑定的引用时，函数式方法的签名（接口中的单个方法）不再与方法引用的签名完全匹配, 因为需要一个对象来调用方法
        // Transform接口的方法比X类的f方法少一个参数X
        // 构建方法时无需理会调用的对象, 实现其他参数的方法即可, 调用对象 参数会在调用时传入
        TransformX tx = X::f;

        // 构建实例对象
        X x = new X();
        // 调用方法时传入实例对象, 实际调用的方法是X类实例对象x的f方法
        // 以下方法类似于transform接收2个参数, 一个是最终调用方法所属的对象, 一个是最终调用方法的参数
        System.out.println(tx.transform(x, "s"));
        // 上述代码等同以下代码
        System.out.println(x.f("s"));
    }
}
