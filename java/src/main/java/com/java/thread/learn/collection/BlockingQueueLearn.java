package com.java.thread.learn.collection;

import com.java.thread.ThreadPool;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://juejin.im/post/6844903602444582920
 * 阻塞队列接口
 * 队列容器已满, 生产者线程阻塞, 直到队列未满
 * 队列容器为空, 消费者线程阻塞, 直到队列非空
 */
public class BlockingQueueLearn extends AbstractQueueTask {

    // 以ArrayBlockingQueue为例, 底层数组实现, FIFO, 创建后容量不可变, 可通过增加一个参数的构造器创建公平队列
    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);

    private static AtomicInteger count = new AtomicInteger(10);

    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    @Override
    public boolean addTask() throws InterruptedException {

        ThreadPool.sleep(1000);
        int countNum = count.decrementAndGet();

        if (countNum < 0) {

            // 当全部任务添加完成后, 修改标志位让处理完成任务的线程能停止处理
            if (atomicBoolean.compareAndSet(false, true))  {
                System.out.println(ThreadPool.currentThreadName("修改了任务处理完成的状态"));
            } else {
                System.out.println(ThreadPool.currentThreadName("无需继续修改任务处理状态"));
            }

            return true;

        } else {

            // 将任务放入队列中
            blockingQueue.put(countNum);
        }
        System.out.println(ThreadPool.currentThreadName("添加了任务".concat(String.valueOf(countNum))));
        return false;
    }

    /**
     * 为了测试不同的情况, 这里手动控制任务何时停止任务+处理
     * @return 是否需要停止任务处理
     */
    @Override
    protected boolean couldStopDealTask() {
        return atomicBoolean.get();
    }

    @Override
    protected void dealTask() throws InterruptedException {

        // 获取队列中待处理的任务, 队列中没有任务时直接返回null,
        // 设置时间后, 设置时间内未返回数据也返回null, 如果获取到任务则会进行处理, 不推荐自己手动进行线程休息
        Integer num = blockingQueue.poll(1000, TimeUnit.MILLISECONDS);
//        Integer num = blockingQueue.poll();
        // 未获取到任务先跳出, 这里不做是否完成任务处理的判断
        if (num == null) {
            return;
        }
        ThreadPool.sleep(100);
        System.out.println(ThreadPool.currentThreadName("处理完成任务".concat(String.valueOf(num))));
    }

    public static void main(String[] args) throws Exception {

        //        long startTime = System.currentTimeMillis();

        BlockingQueueLearn blockingQueueLearn = new BlockingQueueLearn();

        blockingQueueLearn.addTaskToQueue(5);

        blockingQueueLearn.dealTaskToQueue(5);

        //        while (true) {
        //            // 以活跃线程数量等于0作为执行完成标志, 只能在各个线程都能执行完成任务时使用
        //            if (ThreadPool.getInstance().getActiveCount() == 0) {
        //                System.out.println("耗时" .concat(String.valueOf(System.currentTimeMillis() - startTime)));
        //                break;
        //            }
        //        }

        // 销毁线程池
        ThreadPool.destroyThreadPool();
    }
}
