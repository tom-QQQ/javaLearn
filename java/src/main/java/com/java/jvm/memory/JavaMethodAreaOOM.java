package com.java.jvm.memory;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * Java方法区内存溢出
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * java 1.7运行结果 Caused by: java.lang.OutOfMemoryError: PermGen space
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {

        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(o, objects));
            enhancer.create();
        }
    }

    static class OOMObject {

    }
}
