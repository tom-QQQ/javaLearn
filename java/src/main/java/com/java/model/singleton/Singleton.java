package com.java.model.singleton;

/**
 * 类加载时创建对象, 避免多线程时创建多个对象, 但可能会造成可能暂时用不到的对象过早被创建
 * @author Ning
 * @date Create in 2019/3/29
 */
public class Singleton {
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }
}
