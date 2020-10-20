package com.java.on.java8.stream;

import com.java.on.java8.Timer;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class Randoms {

    public static void main(String[] args) {

        Timer timer = new Timer();
        streamPrint();
        timer.duration();
        timer = new Timer();
        foreachPrint();
        timer.duration();
    }

    /**
     * 内部迭代看不到迭代过程, 可读性更强, 能更简单的使用多核处理器
     * 放弃对迭代过程的控制, 可以把控制权交给并行化机制
     * 流是懒加载的, 只在必要时才进行计算, 延迟列表, 可以表示非常大的序列, 无需考虑内存问题
     */
    public static void streamPrint() {


        new Random(47)
                // 产生一个流并限制数据类型和范围
                .ints(5, 20)
                // 去重
                .distinct()
                // 获取前7个
                .limit(7)
                // 排序
                .sorted()
                // 循环打印
                .forEach(System.out::println);
    }

    public static void foreachPrint() {

        Random rand = new Random(47);
        SortedSet<Integer> integers = new TreeSet<>();
        while(integers.size() < 7) {
            int r = rand.nextInt(20);
            if(r < 5) continue;
            integers.add(r);
        }
        System.out.println(integers);
    }

}
