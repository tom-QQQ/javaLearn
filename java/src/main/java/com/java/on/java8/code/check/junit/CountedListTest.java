package com.java.on.java8.code.check.junit;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CountedListTest {

    private CountedList list;

    /**
     * 在任何其他测试操作之前运行一次的方法
     */
    @BeforeAll
    static void beforeAllMsg() {
        System.out.println(">>> Starting CountedListTest");
    }

    /**
     * 所有其他测试操作之后只运行一次的方法
     */
    @AfterAll
    static void afterAllMsg() {
        System.out.println(">>> Finished CountedListTest");
    }

    /**
     * 通常用于创建和初始化公共对象的方法，并在每次测试前运行。可以将所有这样的初始化放在测试类的构造函数中
     */
    @BeforeEach
    public void initialize() {
        list = new CountedList();
        System.out.println("Set up for " + list.getId());
        for (int i = 0; i < 3; i++) {
            list.add(Integer.toString(i));
        }
    }

    /**
     * 如果你必须在每次测试后执行清理（如果修改了需要恢复的静态文件，打开文件需要关闭，打开数据库或者网络连接，etc）
     */
    @AfterEach
    public void cleanUp() {
        System.out.println("Cleaning up" + list.getId());
    }

    @Test
    public void insert() {
        System.out.println("Running insert()");
        assertEquals(list.size(), 3);
        list.add(1, "Insert");
        assertEquals(list.size(), 4);
        assertEquals(list.get(1), "Insert");
    }

    private void compare(List<String> list, String[] strs) {

        assertArrayEquals(list.toArray(new String[0]), strs);
    }

    @Test
    public void order() {
        System.out.println("Running testOrder");
        compare(list, new String[]{"0", "1", "2"});
    }

    @Test
    public void remove() {
        System.out.println("Running testRemove");
        assertEquals(list.size(), 3);
        list.remove(1);
        assertEquals(list.size(), 2);
        compare(list, new String[]{"0", "2"});
    }

    @Test
    public void addAll() {
        System.out.println("Running testAddAll");
        list.addAll(Arrays.asList("An", "African", "Swallow"));
        assertEquals(list.size(), 6);
        compare(list, new String[]{"0", "1", "2", "An", "African", "Swallow"});
    }
}
