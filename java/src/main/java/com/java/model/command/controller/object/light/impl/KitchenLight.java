package com.java.model.command.controller.object.light.impl;


import com.java.model.command.controller.object.light.Light;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class KitchenLight implements Light {

    KitchenLight() {}

    @Override
    public void on() {
        System.out.println("开启了厨房的灯");
    }

    @Override
    public void off() {
        System.out.println("关闭了厨房的灯");
    }
}
