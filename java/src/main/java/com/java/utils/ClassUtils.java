package com.java.utils;


public class ClassUtils {

    public static Class getCurrentClass() {

        /**
         * 这里根据层级, 以{@link com.java.thread.learn.CountDownLatchLearn#main(String[])}调用为例, 会有三层
         * 第一层, 类名为java.lang.Thread
         * 第二层, 类名为com.java.utils.ClassUtils, 即当前类
         * 第三层, 类名为com.java.thread.learn.CountDownLatchLearn, 即调用类
         */
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        // 这里获取到的是包含包的完整的调用类类名
        // 实例方法使用this.getClass().getName()获取
        String currentClassName = stackTraceElements[stackTraceElements.length-1].getClassName();

        try {

            // 获取类需要传入包含包的完整类名
            Class clazz = Class.forName(currentClassName) ;

            return clazz;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
