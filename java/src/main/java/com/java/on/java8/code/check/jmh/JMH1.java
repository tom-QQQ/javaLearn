package com.java.on.java8.code.check.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

// Thread: 该状态为每个线程独享, Benchmark: 该状态在所有线程间共享 Group: 该状态为同一个组里面所有线程共享
@State(Scope.Thread)
// AverageTime平均执行时间, Throughput吞吐量, SampleTime随机取样, 输出结果分布,
// SingleShotTime只运行一次, 同时设置预热次数为0, 测试冷启动, All所有模式都使用
@BenchmarkMode(Mode.AverageTime)
// 时间单位
@OutputTimeUnit(TimeUnit.MICROSECONDS)
// 预热次数, 由于JVM的JIT机制存在, 函数被多次调用后, JVM会尝试将其编译成机器码从而提高执行速度
@Warmup(iterations = 3)
// iterations测试轮数, time每轮时长, timeUnit时长单位
@Measurement(iterations = 3)
// 每个进程中的测试线程，默认为CPU核心数量Runtime.getRuntime().availableProcessors()
//@Threads(value = 12)
// 测试进程数量
@Fork(1)
public class JMH1 {

    private long[] la;

    // 指定参数的多种情况
    @Param({
            "1",
            "10",
            "100",
            "1000",
            "10000",
            "100000",
            "1000000",
            "10000000",
    })
    int size;

    // 初始化数据
    @Setup
    public void setup() {
        la = new long[size];
    }

    public static long f(long x) {
        long quadratic = 42 * x * x + 19 * x + 47;
        return Long.divideUnsigned(quadratic, x + 1);
    }

    // 需要进行测试的方法
    @Benchmark
    public void setAll() {
        Arrays.setAll(la, JMH1::f);
    }

    @Benchmark
    public void parallelSetAll() {
        Arrays.parallelSetAll(la, JMH1::f);
    }


    // 测试结果参考当前目录下的Benchmark.log文件, 执行一次耗时较长, 约16分钟, 2种算法*(预热3次+执行3次)*8种参数
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JMH1.class.getSimpleName())
                .output("E:/Benchmark.log")
                .build();
        new Runner(options).run();
    }
}
