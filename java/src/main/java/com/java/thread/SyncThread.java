package com.java.thread;


/**
 * 秒杀
 * @author Ning
 * @date Create in 2019/10/20
 */
public class SyncThread {

    private static boolean count;

    private static final byte[] b = new byte[0];


    private static void spike() {

        Runnable runnable = () -> {

            ThreadPool.sleep(1000);
            if (count) {
                System.out.println(ThreadPool.currentThreadName("失败"));
                return;
            }

            synchronized (b) {
                if (!count) {
                    count = true;
                    System.out.println(ThreadPool.currentThreadName("成功"));
                } else {
                    System.out.println(ThreadPool.currentThreadName("失败"));
                }
            }
        };

        // 创建的子线程数量
        for (int i = 0; i < 15; i++) {
            ThreadPool.getInstance().execute(runnable);
        }
    }


    public static void main(String[] args) {

        spike();


        ThreadPool.destroyThreadPool();
    }
}


