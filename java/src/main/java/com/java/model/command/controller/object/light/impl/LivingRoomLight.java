package com.java.model.command.controller.object.light.impl;


import com.java.model.command.controller.object.light.Light;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class LivingRoomLight implements Light {

    LivingRoomLight() {}

    @Override
    public void on() {
        System.out.println("开启了卧室的灯");
    }

    @Override
    public void off() {
        System.out.println("关闭了卧室的灯");
    }
}
