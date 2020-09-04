package com.java.thread;

import com.java.utils.ClassUtils;

import java.lang.reflect.Method;

/**
 * 多线程任务模板
 */
public class RunnableTemplate {

    public static void countdown() {

        Runnable runnable = () -> {

            // 复用线程, 不需要时删除即可
            while (true) {

                try {

                    // 执行逻辑

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(ThreadPool.currentThreadName().concat("出现异常"));

                }
            }
        };

        ThreadPool.getInstance().execute(runnable);
        ThreadPool.getInstance().execute(runnable);
        ThreadPool.getInstance().execute(runnable);
    }


    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {

        Class clazz = ClassUtils.getCurrentClass();
        Method method = clazz.getMethod("countdown");
        method.invoke(clazz);
        System.out.println("完成任务");

        ThreadPool.destroyThreadPool();
    }
}
