package com.java.thread.learn;

import com.java.thread.ThreadPool;
import com.java.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏, 可以实现只有当所有线程等待至某个状态后再继续执行之后的逻辑
 */
public class CyclicBarrierLearn {

    public static void countdown(CyclicBarrier cyclicBarrier) {

        Runnable runnable = () -> {

            try {

                System.out.println("线程" + Thread.currentThread().getName().concat("开始执行逻辑处理"));
                Thread.sleep(1000);
                System.out.println("逻辑处理执行完成");

                // 等待其他线程完成任务, 即其他线程不完成任务不会继续执行
                // 即使用该方法, 不能复用线程
                cyclicBarrier.await();
                System.out.println("所有线程处理完成, 开始执行之后的处理");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("线程" + Thread.currentThread().getName().concat("出现异常"));
            }
        };

        ThreadPool.getInstance().execute(runnable);
        ThreadPool.getInstance().execute(runnable);
        ThreadPool.getInstance().execute(runnable);
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {

        try {

            CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
            Class clazz = ClassUtils.getCurrentClass();
            Method method = clazz.getMethod("countdown", CyclicBarrier.class);
            method.invoke(clazz, cyclicBarrier);


            System.out.println("完成任务");

        } catch (Exception e) {
            e.printStackTrace();
        }


        ThreadPool.destroyThreadPool();
    }
}
