package com.java.thread;


/**
 * @author Ning
 * @date Create in 2019/10/20
 */
public class SyncThread implements Runnable {

    private static boolean count;

    private static final byte[] b = new byte[0];

    @Override
    public void run() {
        ThreadPool.sleep(50);
        if (count) {
            System.out.println(String.format("线程[%s]失败", Thread.currentThread().getName()));
            return;
        }
        synchronized (b) {
            if (!count) {
                count = true;
                System.out.println(String.format("线程[%s]成功", Thread.currentThread().getName()));
            } else {
                System.out.println(String.format("线程[%s]失败", Thread.currentThread().getName()));
            }
        }

    }


    public static void main(String[] args) {

        SyncThread syncThread = new SyncThread();

        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());
        ThreadPool.getInstance().execute(new SyncThread());



        while (ThreadPool.getInstance().getActiveCount() != 0) {
            ThreadPool.sleep(50);
        }
        ThreadPool.getInstance().destroy();
    }
}


