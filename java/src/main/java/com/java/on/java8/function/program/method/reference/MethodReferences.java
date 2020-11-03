package com.java.on.java8.function.program.method.reference;

interface Callable {
    void call(String s);
}

class Describe {
    void show(String msg) {
        System.out.println(msg);
    }
}

/**
 * 方法引用
 * 实现方法时引用其他已经实现的方法作为方法实现
 * 需要接口的方法签名(参数类型和返回类型)与引用的方法相同
 */
public class MethodReferences {

    static void hello(String name) {
        System.out.println("Hello, " + name);
    }

    static class Description {
        String about;

        public Description(String about) {
            this.about = about;
        }

        void help(String msg) {
            System.out.println(about + " " + msg);
        }
    }

    static class Helper {
        static void assist(String msg) {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {

        Describe d = new Describe();
        Callable c = d::show;
        c.call("call()");

        c = MethodReferences::hello;
        c.call("Bob");

        c = new Description("valuable")::help;
        c.call("information");

        c = Helper::assist;
        c.call("Help!");
    }
}
