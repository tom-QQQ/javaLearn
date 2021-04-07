package com.java.jvm.memory;

import java.util.LinkedList;
import java.util.List;

/**
 * 限制方法区大小, 间接限制常量池容量产生常量池溢出
 * Java1.6 VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * Java1.7 VM Args: -Xmx:6M
 */
public class RuntimeConstPoolOOM {

    public static void main(String[] args) {
//        oom();
        intern();
    }

    public static void oom() {
        // 使用List保持常量池引用, 避免Full GC回收常量池
        List<String> list = new LinkedList<>();
        int i = 0;
        while (true) {
            // intern方法作用
            // 如果字符串常量池已经包含一个equals此String对象的字符串, 则返回代表池中这个字符串的String对象
            // 否则, 将此String对象包含的字符串添加到常量池中, 并返回此String对象的引用
            list.add(String.valueOf(i++).intern());
        }
    }

    public static void intern() {

        // Java1.6 intern方法把首次遇到的字符串实例复制到永久代的字符串常量池, 返回的也是永久代的常量池引用, StringBuilder创建的
        // 对象在Java堆, 因此Java1.6的2个结果均为false


        // Java1.7 intern方法把首次遇到的字符串实例复制到Java堆中, 常量池记录的是首次出现的实例引用, 因此intern返回的引用和这里创建
        // 的对象是同一个
        // StringBuilder.toString(char[] var1, int var2, int var3)方法参数中不包含String类型对象, 返回的String对象中的字符串
        // 未被添加到常量池中, 因此str.intern()为首次添加到常量池, 常量池存储的就是str的实例引用, 即str对象本身的引用
        String str = new StringBuilder("计算机").append("软件").toString();
        System.out.println("1 " + (str.intern() == str));

        // 这里会返回false因为java在执行StringBuilder.toString()之前就出现过了, 具体是在加载sun.misc.Version这个类的时候进入
        // 常量池的, 字符串常量池已经有它的引用, 不符合首次遇到原则
        str = new StringBuilder("ja").append("va").toString();
        System.out.println("2 " + (str.intern() == str));

        String s = "s";
        // 这里为true, s指向常量池的s, s.intern也指向常量池的s
        System.out.println("3 " + (s.intern() == s));

        String a = "s";
        // 这里为true, a, s都指向常量池的s
        System.out.println("4 " + (a == s));

        String s1 = new String("sss");
        // 上述代码相当于
        // 存放到常量池
        // String temp = "sss";
        // 创建对象, 但创建的对象并不指向常量池
        // String s1 = new String(temp);
        String a2 = "sss";
        // 这里为false, 因为new String创建了2个实例, 一个是s1对象, 一个是常量池里面的sss, s1调用intern返回常量池的sss,
        // 与s1不是同一个对象, 因此返回false
        System.out.println("5 " + (s1.intern() == s1));

        // 这里为true, a2和s1都指向常量池的sss
        System.out.println("6 " + (s1.intern() == a2));

        // s1.intern() == s1等同于这个, false
        System.out.println("7 " + (s1 == a2));
    }
}
