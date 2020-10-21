package com.java.on.java8;

import java.util.Date;

public class Timer {

    private long startTime;

    public Timer() {
//        System.out.println("开始计时");
        this.startTime = new Date().getTime();
    }

    public long duration() {
        long spendTime = new Date().getTime() - startTime;
        System.out.println("耗时".concat(String.valueOf(spendTime)));
        return spendTime;
    }
}
