package com.java.on.java8.code.check.junit;

import java.util.ArrayList;

/**
 * 追踪有多少个 CountedLists 被创建
 */
public class CountedList extends ArrayList<String> {

    private static int counter = 0;
    private int id = counter++;

    public CountedList() {
        System.out.println("CounterList #".concat(String.valueOf(id)));
    }

    public int getId() {
        return id;
    }
}
