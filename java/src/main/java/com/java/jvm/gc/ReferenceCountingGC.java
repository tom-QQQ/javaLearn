package com.java.jvm.gc;

/**
 * 引用计数法GC
 * 打印GC日志
 * VM Args: -XX:+PrintGCDetails
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

       System.gc();
       // 结果是被回收
    }
}
