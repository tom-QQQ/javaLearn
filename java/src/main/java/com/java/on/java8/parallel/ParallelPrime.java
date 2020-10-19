package com.java.on.java8.parallel;
import com.java.on.java8.Timer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class ParallelPrime {

    static final int COUNT = 100_000;

    /**
     * 判断是否是质数
     * @param n 数字
     * @return 是否是质数
     */
    public static boolean isPrime(long n) {

        // 判断一个数n是不是质数只需要判断到√n即可, 大概原因是如果n是合数, 那必定存在2个数的乘积等于n, 这2个数必定一个大于√n, 一个
        // 小于√n, 或者这2个数都等于√n, 因此只需要判断到√n即可
        // rangeClosed 设置计算范围返回一个LongStream对象
        // noneMatch 判断结果都为false时返回true
        return LongStream.rangeClosed(2, (long)(Math.sqrt(n))).noneMatch(i -> n % i == 0);
    }

    public static void main(String[] args) throws IOException {

        Timer timer = new Timer();
        List<String> primers =
                // 设置循环
                LongStream.iterate(2, i -> i+1)
                 // 开启并行
                .parallel()
                 // 筛选数据
                .filter(ParallelPrime::isPrime)
                 // 限制总数
                .limit(COUNT)
                 // long转换成String
                .mapToObj(Long::toString)
                 // String流转换成List
                .collect(Collectors.toList());

        System.out.println(timer.duration());
        System.out.println(primers);
    }


}
