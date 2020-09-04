package com.java.thread.learn;

import com.java.thread.ThreadPool;
import com.java.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.Semaphore;

/**
 * 信号量, 可以控制同时访问的线程个数
 */
public class SemaphoreLearn {

    public static void main(String[] args) throws Exception {

        // 使用构造器构造, 参数为最多允许线程访问的数量, 可以补充参数构建公平信号量
        Semaphore semaphore = new Semaphore(4);


        /**
         * 执行多线程任务{@link #countdown}
         */
        runThreadTask(semaphore);


        // 销毁线程池
        ThreadPool.destroyThreadPool();
    }

    /**
     * 通过获取和释放信号量控制线程访问
     * @param semaphore 信号量
     */
    public static void countdown(Semaphore semaphore) {

        Runnable runnable = () -> {

            try {

                /**
                 * 获取和释放的信号量, 必须相同!!!
                 */
                int semaphoreNum = 2;



                // 获取信号量以执行之后的逻辑, 未获取时到会一直等待, 这里获取多少个, 之后就要释放多少个
                semaphore.acquire(semaphoreNum);



                // 处理逻辑
                performTask(semaphoreNum);



                // 释放获取的信号量, 必须和之前获取信号量的数量相同
                semaphore.release(semaphoreNum);



                // 收尾任务
                finishTask(semaphoreNum);

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(ThreadPool.currentThreadName("出现异常"));
            }
        };

        // 使用自定义线程池创建的子线程数量
        for (int i = 0; i < 8; i++) {
            ThreadPool.getInstance().execute(runnable);
        }
    }

    private static void performTask(int semaphoreNum) {

        System.out.println(ThreadPool.currentThreadName("获得到".concat(String.valueOf(semaphoreNum)).concat("个信号量")));
        ThreadPool.sleep(1000);
        System.out.println(ThreadPool.currentThreadName("逻辑处理完成"));
    }

    private static void finishTask(int semaphoreNum) {
        System.out.println(ThreadPool.currentThreadName("释放".concat(String.valueOf(semaphoreNum)).concat(
                "个信号量")));
    }

    @SuppressWarnings("all")
    private static void runThreadTask(Semaphore semaphore) throws Exception {

        Class clazz = ClassUtils.getCurrentClass();
        Method method = clazz.getMethod("countdown", Semaphore.class);
        method.invoke(clazz, semaphore);
    }
}
