package com.java.model.command.controller.object.light.impl;


import com.java.model.command.controller.object.light.Light;

/**
 * @author Ning
 * @date Create in 2019/3/30
 */
public class BaseLight implements Light {

    @Override
    public void on() {
        System.out.println("灯开了");
    }

    @Override
    public void off() {
        System.out.println("灯关了");
    }
}
