package com.java.other;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 使用Fork/Join并行执行框架计算指定范围内全部数字的和
 * Fork/Join框架用于将大任务分割为若干个小任务分别去执行, 汇总小任务的结果得到大任务结果, 用到了工作窃取算法
 * @author Ning
 * @date Create in 2019/1/14
 */
public class ForkJoinTask extends RecursiveTask<Integer> {

    /**
     * 阈值
     */
    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    private ForkJoinTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;

        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }

        } else {

            int middle = (start + end) / 2;
            ForkJoinTask leftTask = new ForkJoinTask(start, middle);
            ForkJoinTask rightTask = new ForkJoinTask(middle + 1, end);

            leftTask.fork();
            rightTask.fork();

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }

        return sum;
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        ForkJoinTask task = new ForkJoinTask(1, 5);
        Future<Integer> result = forkJoinPool.submit(task);

        try {
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
