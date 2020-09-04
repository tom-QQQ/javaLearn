package com.java.thread.learn;

import com.java.thread.ThreadPool;
import com.java.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计数器, 可以实现代码逻辑在指定个线程完成之后再执行的功能
 */
public class CountDownLatchLearn {

    public static void main(String[] args) throws Exception {

        // 构建计数器参数
        CountDownLatch latch = new CountDownLatch(taskNum);

        /**
         * 执行多线程任务{@link #countdown}
         */
        runThreadTask(latch);

        // 等待计数器小于0
        latch.await();
        // 设置超时时间, 在超时时间内未完成则执行之后的逻辑, 不影响依然在执行的线程
//        latch.await(1, TimeUnit.MILLISECONDS);


        // 子线程任务都执行完成后需要执行的任务
        afterTask();

        ThreadPool.destroyThreadPool();
    }

    /**
     * 任务数量
     */
    private static int taskNum = 3;

    /**
     * 确保每次只被一个线程修改
     */
    private static AtomicInteger count = new AtomicInteger(taskNum);


    public static void countdown(CountDownLatch latch) {

        Runnable runnable = () -> {

            // 无限循环, 确保执行线程数低于任务数量时也能执行完成任务
            while (true) {

                // 执行任务
                task();

                // 每次执行完成后计数器减一
                latch.countDown();

                // 作用为: 还有未执行完成的任务时, 当前线程就获取任务执行, 否则跳出
                if (getRemainingTaskNum() <= 0) {
                    System.out.println("任务执行完成, 无需继续执行");
                    return;
                }
            }
        };

        // 使用自定义线程池创建的子线程数量
        for (int i = 0; i < 2; i++) {
            ThreadPool.getInstance().execute(runnable);
        }
    }

    private static void task() {
        ThreadPool.sleep(1000);
        System.out.println(ThreadPool.currentThreadName("完成任务"));
    }

    /**
     * 获取未执行的任务数量
     * @return 未执行的任务数量
     */
    private static int getRemainingTaskNum() {
        int value = count.decrementAndGet();
        System.out.println(value);
        return value;
    }

    private static void afterTask() {
        System.out.println("线程任务全部执行完成");
    }

    @SuppressWarnings("all")
    private static void runThreadTask(CountDownLatch latch) throws Exception {

        Class clazz = ClassUtils.getCurrentClass();
        Method method = clazz.getMethod("countdown", CountDownLatch.class);
        // 使用类名.方法名调用静态方法
        method.invoke(clazz, latch);
    }
}
