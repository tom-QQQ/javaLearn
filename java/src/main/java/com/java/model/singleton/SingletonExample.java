package com.java.model.singleton;

/**
 * 推荐的方法, 兼顾性能, 延迟对象构建和多线程可用
 * @author Ning
 * @date Create in 2019/3/29
 */
public class SingletonExample {

    /**
     * volatile关键字确保当singletonExample被初始化为实例时, 相关的初始化操作可以顺序执行(主要是第28行代码编译后会变成3行代码(分配
     *  内存空间, 初始化对象, 设置instance指向之前刚才分配对象的内存地址), 没有volatile的修饰, 这3行代码可能会进行指令重排,
     * 造成先指向对象内存地址, 再初始化对象, 就可能有线程只拿到了一个未初始化完成的对象)
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
