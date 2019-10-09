package com.java.model.command.controller.object.ceiling.fan.impl;

import com.java.model.command.controller.object.ceiling.fan.CeilingFan;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class LivingRoomCeilingFan implements CeilingFan {


    private int speed;

    LivingRoomCeilingFan() {
        speed = OFF;
    }

    @Override
    public void adjustHigh() {
        speed = HIGH;
        System.out.println("卧室的电扇调到高档");
    }

    @Override
    public void adjustMedium() {
        speed = MEDIUM;
        System.out.println("卧室的电扇调到中档");
    }

    @Override
    public void adjustLow() {
        speed = LOW;
        System.out.println("卧室的电扇调到低档");
    }

    @Override
    public void off() {
        speed = OFF;
        System.out.println("关闭卧室的电扇");
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
