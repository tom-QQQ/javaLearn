package com.java.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 公共线程池, 使用execute()方法执行那些不需要返回值的线程, 使用submit()执行那些需要返回值的线程
 * @author Ning
 * @date Create in 2019/10/20
 */
public class ThreadPool {

    private volatile static ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * ThreadPoolTaskExecutor的处理流程 当池子大小小于corePoolSize，就新建线程，并处理请求 当池子大小等于corePoolSize，
     * 把请求放入workQueue中，池子里的空闲线程就去workQueue中取任务并处理 当workQueue放不下任务时，就新建线程入池，并处理请求，
     * 如果池子大小撑到了maximumPoolSize，就用RejectedExecutionHandler来做拒绝处理 当池子的线程数大于corePoolSize时，
     * 多余的线程会等待keepAliveTime长时间，如果无请求可处理就自行销毁
     *
     * @return 线程池
     */
    public static ThreadPoolTaskExecutor getInstance() {

        if (threadPoolTaskExecutor == null) {

            synchronized (ThreadPool.class) {

                if (threadPoolTaskExecutor == null) {

                    threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
                    threadPoolTaskExecutor.setCorePoolSize(13);
                    threadPoolTaskExecutor.setMaxPoolSize(25);
                    threadPoolTaskExecutor.setQueueCapacity(2048);
                    // 设置任务数量超过队列长度的处理策略, 抛弃任务
                    threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
                    threadPoolTaskExecutor.setKeepAliveSeconds(20);

                    // 设置线程池被销毁时, 如果有子线程任务未执行完成, 则继续执行任务
                    threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
                    // 线程池销毁前等待子线程执行完成的最长时间, 不设置默认一直等待
                    threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
                    threadPoolTaskExecutor.initialize();

                    /**
                     * 设置线程名前缀, 在线程名打印{@link #currentThreadName}时显示的就是 "线程"+"线程名前缀"+"线程编号"
                     */
                    threadPoolTaskExecutor.setThreadNamePrefix("");
                    return threadPoolTaskExecutor;
                }
            }
        }

        return threadPoolTaskExecutor;
    }

    /**
     * 销毁线程池
     */
    public static void destroyThreadPool() {

        // 这里不能直接销毁线程池, 否则会抛出InterruptedException, 线程被其他线程(主线程)中断
        // 如果线程池配置了waitForJobsToCompleteOnShutdown为true, 则如果线程池销毁时仍有线程在执行,
        // 则会等待一段时间(可设置), 后再销毁线程池
//        while (!(ThreadPool.getInstance().getActiveCount() == 0)) {
//            sleep(50);
//        }

        // 必须销毁线程池, 否则程序执行不会停止
        // 这里仅仅是发起了线程池销毁, 如果配置了如果线程池配置了waitForJobsToCompleteOnShutdown为true,
        // 则只有当线程池内的线程的任务执行完成后或到超时时间才会真正销毁线程池
        ThreadPool.getInstance().destroy();
        System.out.println('\n' + "线程池发起销毁操作");
    }

    /**
     * 休息, 捕获了异常
     * @param sleepTime 休息时间
     */
    public static void sleep(long sleepTime) {

        try {
            Thread.sleep(sleepTime);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(ThreadPool.currentThreadName("被中断了"));
        }
    }

    /**
     * 获取当前线程名
     * @return 当前线程名
     */
    public static String currentThreadName(String content) {

        return "线程".concat(Thread.currentThread().getName().concat(" ").concat(content));
    }
}
