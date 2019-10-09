package com.java.model.command.controller.controller;

import java.util.LinkedList;

/**
 * 固定长度的队列, 长度满时, 会删除队列末尾处的数据
 * @author Ning
 * @date Create in 2019/3/31
 */
public class LinkedQueue<T> {

    private LinkedList<T> queue;
    private int length;

    LinkedQueue(int length) {
        queue = new LinkedList<>();
        this.length = length;
    }

    public void offer(T t) {

        if (size() > length) {
            queue.poll();
        }
        queue.offer(t);
    }

    public int size() {
        return queue.size();
    }

    public T getLast() {
        return queue.getLast();
    }

    public void poolLast() {
        queue.pollLast();
    }
}
