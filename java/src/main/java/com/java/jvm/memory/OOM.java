package com.java.jvm.memory;

import java.util.LinkedList;
import java.util.List;

/**
 * 减少内存分配产生内存溢出异常
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class OOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new LinkedList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
