package com.java.thread.learn.collection;

import com.java.thread.ThreadPool;

public abstract class AbstractQueueTask {

    /**
     * 添加任务的执行逻辑
     * @return 是否需要停止任务添加
     */
    protected abstract boolean addTask() throws InterruptedException;

    /**
     * 处理任务
     */
    protected abstract void dealTask() throws InterruptedException;

    /**
     * 停止任务处理的逻辑
     * @return 是否停止任务处理
     */
    protected abstract boolean couldStopDealTask();


    protected void addTaskToQueue(int threadNum) {

        Runnable runnable = () -> {

            // 复用线程, 不需要时删除即可
            while (true) {

                try {

                    // 执行任务
                    if (addTask()) {
                        System.out.println(ThreadPool.currentThreadName("任务获取完成"));
                        return;
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(ThreadPool.currentThreadName("出现异常"));

                }
            }
        };

        // 使用自定义线程池创建的子线程数量
        for (int i = 0; i < threadNum; i++) {
            ThreadPool.getInstance().execute(runnable);
        }
    }

    protected void dealTaskToQueue(int threadNum) {

        Runnable runnable = () -> {

            // 复用线程, 不需要时删除即可
            while (true) {

                try {

                    if (couldStopDealTask()) {
                        System.out.println(ThreadPool.currentThreadName("任务执行完成"));
                        return;
                    }

                    // 处理任务
                    dealTask();

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(ThreadPool.currentThreadName("出现异常"));

                }
            }
        };

        // 使用自定义线程池创建的子线程数量
        for (int i = 0; i < threadNum; i++) {
            ThreadPool.getInstance().execute(runnable);
        }
    }
}
