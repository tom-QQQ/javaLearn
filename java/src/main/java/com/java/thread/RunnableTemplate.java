package com.java.thread;

import com.java.utils.ClassUtils;

import java.lang.reflect.Method;

/**
 * 多线程任务模板
 */
public class RunnableTemplate {

    public static void main(String[] args) throws Exception {

        /**
         * 执行多线程任务{@link #countdown}
         */
        runThreadTask();


        // 销毁线程池
        ThreadPool.destroyThreadPool();
    }

    public static void countdown() {

        Runnable runnable = () -> {

            // 复用线程, 不需要时删除即可
            while (true) {

                try {

                    // 执行任务
                    task();


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(ThreadPool.currentThreadName("出现异常"));

                }
            }
        };

        // 使用自定义线程池创建的子线程数量
        for (int i = 0; i < 3; i++) {
            ThreadPool.getInstance().execute(runnable);
        }
    }

    private static void task() {
        ThreadPool.sleep(1000);
        System.out.println(ThreadPool.currentThreadName("完成任务"));
    }

    @SuppressWarnings("all")
    private static void runThreadTask() throws Exception {

        Class clazz = ClassUtils.getCurrentClass();
        Method method = clazz.getMethod("countdown");
        // 使用类名.方法名调用静态方法
        method.invoke(clazz);
    }
}
