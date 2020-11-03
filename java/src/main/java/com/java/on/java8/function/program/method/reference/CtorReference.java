package com.java.on.java8.function.program.method.reference;

class Dog {

    String name;
    int age = -1;

    Dog() {
        name = "stray";
    }

    Dog(String name) {
        this.name = name;
    }

    Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

interface MakeNoArgs {
    Dog make();
}

interface Make1Args {
    Dog make(String name);
}

interface Make2Args {
    Dog make(String name, int age);
}

public class CtorReference {

    public static void main(String[] args) {

        // 3个构造器接口不同, 但编译器可以知道需要使用哪个构造函数
        MakeNoArgs makeNoArgs = Dog::new;
        Make1Args make1Args = Dog::new;
        Make2Args make2Args = Dog::new;

        Dog dn = makeNoArgs.make();
        Dog d1 = make1Args.make("Comet");
        Dog d2 = make2Args.make("Ralph", 4);
    }
}
