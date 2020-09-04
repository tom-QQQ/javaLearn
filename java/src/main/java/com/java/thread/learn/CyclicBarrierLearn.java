package com.java.thread.learn;

import com.java.thread.ThreadPool;
import com.java.utils.ClassUtils;

import java.lang.reflect.Method;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 回环栅栏, 可以在多线程下, 让子线程全部执行完一部分逻辑后, 再执行之后的逻辑
 * 可以重用: 指定线程数量低于总子线程数量; 一个任务内使用多次
 */
public class CyclicBarrierLearn {


    public static void main(String[] args) throws Exception {

        // 构建回环栅栏
        CyclicBarrier cyclicBarrier = createCyclicBarrier();

        /**
         * 执行多线程任务{@link #countdown}
         */
        runThreadTask(cyclicBarrier);


        // 销毁线程池
        ThreadPool.destroyThreadPool();
    }

    public static void countdown(CyclicBarrier cyclicBarrier) {

        Runnable runnable = () -> {

            try {

                // 第一部分任务
                firstPartTask();



                // 使用分隔栅栏等待其他线程完成任务, 即其他线程不完成任务不会继续执行, 使用该方法, 不能复用线程
                // 设置超时时间后, 时间内未执行完成会抛出异常, 详见之后的异常捕获, cyclicBarrier.await()不超时
                cyclicBarrier.await(100, TimeUnit.MILLISECONDS);



                // 第二部分任务
                secondPartTask();



                // 再次使用分隔栅栏
                cyclicBarrier.await(100, TimeUnit.MILLISECONDS);



                // 最后一部分任务
                lastPartTask();


                // await出现超时, 第一个线程抛出该异常
            } catch (TimeoutException e) {
                System.out.println(ThreadPool.currentThreadName("出现超时异常"));


                // 之后的线程都抛出这个异常
                // 当某个线程试图等待处于断开状态的 barrier 时，或者 barrier 进入断开状态而线程处于等待状态时，抛出该异常。
            } catch (BrokenBarrierException e) {
                System.out.println(ThreadPool.currentThreadName("出现BrokenBarrierException异常"));


            }  catch (Exception e) {
                e.printStackTrace();
                System.out.println(ThreadPool.currentThreadName("出现其他异常"));
            }
        };

        // 使用自定义线程池创建的子线程数量
        for (int i = 0; i < 4; i++) {
            ThreadPool.getInstance().execute(runnable);
        }
    }

    private static void firstPartTask() {

        // 前半部分任务
        System.out.println(ThreadPool.currentThreadName("开始执行逻辑处理"));
        ThreadPool.sleep(1000);
        System.out.println(ThreadPool.currentThreadName("逻辑处理执行完成"));
    }

    private static void secondPartTask() {
        // 后半部分任务
        System.out.println(ThreadPool.currentThreadName("处理完成, 开始执行之后的处理"));
        ThreadPool.sleep(1);
    }

    private static void lastPartTask() {
        System.out.println(ThreadPool.currentThreadName("执行最终任务"));
        ThreadPool.sleep(1);
    }

    @SuppressWarnings("all")
    private static void runThreadTask(CyclicBarrier cyclicBarrier) throws Exception {

        Class clazz = ClassUtils.getCurrentClass();
        Method method = clazz.getMethod("countdown", CyclicBarrier.class);
        // 使用类名.方法名调用静态方法
        method.invoke(clazz, cyclicBarrier);
    }

    /**
     * 第一个参数指定等待的线程数量
     * 第二个参数是全部线程前半部分任务执行完成后, 还需要执行的任务, 会分配给某一个线程完成
     * 每指定个线程的前半部分任务执行完成, 都会让某个线程执行一次这里的任务, 然后再执行后半部分任务
     * 如果剩余的线程小于等待线程的数量, 则这些线程后半部分任务无法执行完成
     * @return 回环栅栏
     */
    private static CyclicBarrier createCyclicBarrier() {

        return new CyclicBarrier(2, () -> {
            System.out.println(ThreadPool.currentThreadName("执行CyclicBarrier分配的额外任务"));
        });
    }
}
