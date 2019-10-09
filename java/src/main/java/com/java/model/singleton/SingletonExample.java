package com.java.model.singleton;

/**
 * 推荐的方法, 兼顾性能, 延迟对象构建和多线程可用
 * @author Ning
 * @date Create in 2019/3/29
 */
public class SingletonExample {

    /**
     * volatile关键字确保当singletonExample被初始化为实例时, 多个线程能正确地处理该变量
     */
    private volatile static SingletonExample singletonExample;

    private SingletonExample(){}

    /**
     * 使用双重检查锁
     * @return 单例对象
     */
    public static SingletonExample getInstance() {

        if (singletonExample == null) {

            synchronized (SingletonExample.class) {
                if (singletonExample == null) {
                    return new SingletonExample();
                }
            }
        }
        return singletonExample;
    }
}
