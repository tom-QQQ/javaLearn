package com.java.thread.learn;

import com.java.thread.ThreadPool;
import com.java.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 回环栅栏, 可以实现只有当所有线程执行完成cyclicBarrier.await()之前的逻辑后再执行之后的逻辑
 * 可以重用: 指定线程数量低于总子线程数量; 一个任务内使用多次
 */
public class CyclicBarrierLearn {

    public static void countdown(CyclicBarrier cyclicBarrier) {

        Runnable runnable = () -> {

            try {

                // 前半部分任务
                System.out.println(ThreadPool.currentThreadName().concat("开始执行逻辑处理"));
                Thread.sleep(1000);
                System.out.println(ThreadPool.currentThreadName().concat("逻辑处理执行完成"));

                // 等待其他线程完成任务, 即其他线程不完成任务不会继续执行
                // 即: 使用该方法, 不能复用线程
//                cyclicBarrier.await();
                // 设置超时时间后, 在指定时间内当前组其他线程未执行完成时执行完成的线程会抛出异常, 详见之后的异常捕获
                cyclicBarrier.await(10, TimeUnit.MILLISECONDS);

                // 后半部分任务
                System.out.println(ThreadPool.currentThreadName().concat("处理完成, 开始执行之后的处理"));

                // 甚至可以多次使用
                Thread.sleep(1000);
                cyclicBarrier.await(10, TimeUnit.MILLISECONDS);
                System.out.println(ThreadPool.currentThreadName().concat("执行最终任务"));

                // await出现超时, 第一个线程抛出该异常
            } catch (TimeoutException e) {
                e.printStackTrace();
                System.out.println(ThreadPool.currentThreadName().concat("出现超时异常"));

                // 之后的线程都抛出这个异常
                // 当某个线程试图等待处于断开状态的 barrier 时，或者 barrier 进入断开状态而线程处于等待状态时，抛出该异常。
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
                System.out.println(ThreadPool.currentThreadName().concat("出现BrokenBarrierException异常"));

            }  catch (Exception e) {
                e.printStackTrace();
                System.out.println(ThreadPool.currentThreadName().concat("出现其他异常"));
            }
        };

        ThreadPool.getInstance().execute(runnable);
        ThreadPool.getInstance().execute(runnable);
        ThreadPool.getInstance().execute(runnable);
        ThreadPool.getInstance().execute(runnable);
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {

        try {

            // 第一个参数指定等待的线程数量, 必须和线程数量相同, 否则会无限等待
            // 第二个参数是全部线程前半部分任务执行完成后, 还需要执行的任务, 会分配给某一个线程完成
            // 即每指定个线程的前半部分任务执行完成, 都会执行一次这里的任务, 然后再执行后半部分任务
            // 如果剩余的线程小于等待线程的数量, 则这些线程后半部分任务无法执行完成
            CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
                System.out.println(ThreadPool.currentThreadName().concat("执行CyclicBarrier分配的额外任务"));
            });


            Class clazz = ClassUtils.getCurrentClass();
            Method method = clazz.getMethod("countdown", CyclicBarrier.class);
            // 使用类名.方法名调用静态方法
            method.invoke(clazz, cyclicBarrier);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ThreadPool.destroyThreadPool();
    }
}
