package com.java.thread.learn;

import com.java.thread.ThreadPool;
import com.java.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchLearn {

    private static int taskNum = 3;

    /**
     * 计数器, 小于等于0时执行才能之后的逻辑
     */
    private static CountDownLatch latch = new CountDownLatch(taskNum);

    /**
     * 确保每次只被一个线程修改
     */
    private static AtomicInteger count = new AtomicInteger(taskNum);

    public static void countdown() {

        Runnable runnable = () -> {

            // 无限循环, 确保执行线程数低于任务数量时也能执行完成任务
            while (true) {

                try {
                    Thread.sleep(1000);
                    System.out.println(ThreadPool.currentThreadName().concat("完成任务"));

                    int value = count.decrementAndGet();
                    System.out.println(value);
                    // 小于等于0时也执行一次countDown(), 确保计数器的值能小于等于0
                    if (value <= 0) {
                        System.out.println("任务执行完成, 无需继续执行");
                        latch.countDown();
                        return;

                    } else {
                        latch.countDown();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(ThreadPool.currentThreadName().concat("被中断"));
                    return;
                }
            }
        };

        ThreadPool.getInstance().execute(runnable);
        ThreadPool.getInstance().execute(runnable);
//        ThreadPool.getInstance().execute(runnable);
    }

    public static void main(String[] args) throws Exception {


        Class clazz = ClassUtils.getCurrentClass();
        Method method = clazz.getMethod("countdown");
        // 使用类名.方法名调用静态方法
        method.invoke(clazz);

        System.out.println(latch.getCount());
        // 等待计数器小于0, 该方法也会抛出线程中断异常
        latch.await();
        // 设置超时时间, 在超时时间内未完成则执行之后的逻辑, 不影响依然在执行的线程
//        latch.await(1, TimeUnit.MILLISECONDS);
        System.out.println("完成任务");

        ThreadPool.destroyThreadPool();
    }
}
