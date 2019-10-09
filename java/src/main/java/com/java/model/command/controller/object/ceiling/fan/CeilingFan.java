package com.java.model.command.controller.object.ceiling.fan;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public interface CeilingFan {

    int HIGH = 3;
    int MEDIUM = 2;
    int LOW = 1;
    int OFF = 0;

    /**
     * 调至高档
     */
    void adjustHigh();

    /**
     * 调至中档
     */
    void adjustMedium();

    /**
     * 调至低档
     */
    void adjustLow();

    /**
     * 关闭
     */
    void off();

    /**
     * 获取挡位
     * @return 挡位
     */
    int getSpeed();
}
